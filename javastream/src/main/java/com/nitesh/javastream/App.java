package com.nitesh.javastream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	List<String> names = Arrays.asList("Nitesh", "Kishan", "Raj", "Nikunj", "Sagar");
    	
    	names.stream().filter(player -> player.equals("Raj"))
    	.forEach(player -> System.out.println("Yes. Raj"));
    	
    	Optional<String> optional = names.stream().filter(player->player.equals("Raj"))
    	.findFirst();
    	
    	if(optional.isPresent()) {
    		System.out.println("Yes");
    	}
    	
        System.out.println( "Hello World!" );
        
        List<String> strings = names.stream().filter(obj -> obj.contains("s"))
        		.peek(obj -> System.out.println("Peeked : "+obj)) // Used for log 
        .collect(Collectors.toList());
        
        
        System.out.println(strings);
        
        IntStream.of(1,23,4,5,6,8,15)
        .skip(2)
        .filter(i->i>5)
        .forEach(i->System.out.println(i));
        
    }
}
