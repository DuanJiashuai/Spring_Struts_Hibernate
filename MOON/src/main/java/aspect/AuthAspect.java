package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AuthAspect {

	@Before("execution(* service.impl.*.*(..))")
	public void authority() {
		System.out.println("======正在模拟执行权限检查======");
	}
}
