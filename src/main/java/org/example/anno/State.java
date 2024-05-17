package org.example.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validation.StateValidation;

import java.lang.annotation.*;

@Documented //元注解，表示注解会被包含在javadoc中
@Constraint(
        validatedBy = {StateValidation.class}
)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    String message() default "{jakarta.validation.constraints.State.message}";
    //指定分组
    Class<?>[] groups() default {};
    //负载 用于携带一些元数据信息
    Class<? extends Payload>[] payload() default {};
}
