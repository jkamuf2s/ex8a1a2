/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.jkjh.controller;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author JAYDESKTOP
 */
@Named
@ApplicationScoped
public class UserController {
    
        public String getMessage() {
        return "Hello World!";
    }
    
}
