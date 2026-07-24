package spring_boot_web_hexagon_arch.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//Al iniciar el proycto spring boot escaneara y buscara aqui en configuracion y traera las configuracion qeu tenga esta clase
@Configuration
@EnableAsync
@EnableScheduling
public class ApplicationConfig {
}
