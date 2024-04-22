package com.blogapp.blog.Exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * ResourceNotFoundException
 */
@Getter
@Setter
public class ResourceNotFoundException  extends RuntimeException{


    String ResourceName;
    String fieldName;
    Long filedValue;

    public ResourceNotFoundException(String ResourceName, String fieldName,long fieldValue)
    { 
        super(String.format("%s not found with  %s : %d", ResourceName,fieldName,fieldValue));
        this.ResourceName = ResourceName;
        this.fieldName = fieldName;
        this.filedValue=fieldValue;
    }

}
