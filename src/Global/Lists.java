/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import Global.Game;
import Global.CartItem;
import Global.User;
import Global.Discount;

import java.util.HashMap;
import java.util.Map;

import java.util.*;
//Import games so we can make an array list off of them
/**
 *
 * This class is only meant to hosts lists that will be used throughout the program or can be. Things like games, cart, users, discounts, and even images 
 * are all here and accounted for with a few others.
 */
public class Lists {
    
    //This is a global class that will host lists
    
    //A list of games
    public static ArrayList<Game> games = new ArrayList<Game>();
    
    //A list for a cart
    public static ArrayList<CartItem> cart = new ArrayList<CartItem>();
    
    //These next two are lists of consoles and genres to help with filters
    public static ArrayList<String> consoles = new ArrayList<String>();
    public static ArrayList<String> genres = new ArrayList<String>();
    
    //A list for users
    public static ArrayList<User> users = new ArrayList<User>();
    
    //A list for discounts
    public static ArrayList<Discount> discounts = new ArrayList<Discount>();
    
    //this list will be for images
    public static ArrayList<Object[]> images = new ArrayList<Object[]>();
    
    //list for the orders
    public static ArrayList<Order> orders = new ArrayList<Order>();
}
