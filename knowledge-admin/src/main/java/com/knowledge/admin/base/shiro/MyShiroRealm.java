package com.knowledge.admin.base.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.feilong.core.bean.ConvertUtil;
import com.knowledge.admin.base.resource.entity.TbResource;
import com.knowledge.admin.base.resource.service.ITbResourceService;
import com.knowledge.admin.base.user.entity.TbUser;
import com.knowledge.admin.base.user.service.ITbUserService;

/**
 *  身份校验核心类;
 *  @author francis
 */
public class MyShiroRealm extends AuthorizingRealm{
	
	@Resource
	private ITbUserService userService;
	
	@Resource
	private ITbResourceService resourceService;

	/**
	 * 认证信息,认证回调函数,登录时调用
	 * 首先根据传入的用户名获取User信息；然后如果user为空，那么抛出没找到帐号异常UnknownAccountException；
	 * 如果user找到但锁定了抛出锁定异常LockedAccountException；最后生成AuthenticationInfo信息，
	 * 交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配，
	 * 如果不匹配将抛出密码错误异常IncorrectCredentialsException；
	 * 另外如果密码重试次数太多将抛出超出重试次数异常ExcessiveAttemptsException；
	 * 在组装SimpleAuthenticationInfo信息时， 需要传入：身份信息（用户名）、凭据（密文密码）、加密盐（username+salt），
	 * CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
			//获取用户的输入的账号.
	       String userName = (String)token.getPrincipal();
	       TbUser userInfo = userService.selectUserRole(ConvertUtil.toMap("accountName",(Object)userName));
	       if(userInfo == null){
	    	   throw new UnknownAccountException();// 没找到帐号
	       }
	       if (userInfo.getLocked()==2) {
				throw new LockedAccountException(); // 帐号被锁定
			}
			// 从数据库查询出来的账号名和密码,与用户输入的账号和密码对比
			// 当用户执行登录时,在方法处理上要实现subject.login(token);
			// 然后会自动进入这个类进行认证
			// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得shiro自带的不好可以自定义实现
	       SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	    		   userInfo, // 用户对象
	    		   userInfo.getPassword(), // 密码
				   ByteSource.Util.bytes(userName + userInfo.getCredentialsSalt()),// salt=username+salt
				   getName() // realm name
			);
		    // 当验证都通过后，把用户信息放在session里
			Session session = SecurityUtils.getSubject().getSession();
			// 用户对象
			session.setAttribute("userSession", userInfo);
	       return authenticationInfo;
	}

	/**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * @param principals
     * @return
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		 /*
	        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
	        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
	        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
	        * 缓存过期之后会再次执行。
	        */
	       TbUser user  = (TbUser)principals.getPrimaryPrincipal();
	       if (user != null) {
	    	   List<TbResource> resourceList = resourceService.findResourcesByUserId(user.getId());
	    	   SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	    	   info.addRole(user.getRole().getName());
	    	   for (TbResource resourceEntity : resourceList) {
					info.addStringPermission(resourceEntity.getSourceKey());
				}
	    	   return info;
	       }
	       return null;
	    }
}
