package com.mypkg.abc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 
    	        
    	        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    	        
    	        PaymentProcessor processor = (PaymentProcessor) context.getBean("paymentProcessor");

    	        
    	        processor.process();
    }
}
