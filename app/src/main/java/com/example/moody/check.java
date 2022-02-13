package com.example.moody;

import static com.example.moody.RegisterActivity.pass1;
import static com.example.moody.RegisterActivity.pass2;
import static com.example.moody.RegisterActivity.regUser;

public class check
{

   public static boolean flag_check_special_symbol=false;

    public static boolean is_Valid_Password()
    {
        if (pass1.length() < 6)
        {
            //flag_pass_length=false;
            return false;
        }
        if (regUser.length() < 10)
        {
            //flag_username_length=false;
            return false;
        }
        if(!pass1.equals(pass2))
        {
            //flag_confirm_password_wrong=false;
            return false;
        }
        if(regUser.equals(pass1))
        {
            //flag_username_password_same=true;
            return false;
        }
        if(pass1.equals("123456")||pass1.equals("654321")||pass1.equals("Pass@123"))
        {
            //flag_easy_password=true;
            return false;
        }

        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < pass1.length(); i++)
        {

            char ch = pass1.charAt(i);

            if (is_Numeric(ch))
            {
                numCount++;
            }
            else if(is_Letter(ch))
            {
                charCount++;
            }
            else
            {
                flag_check_special_symbol=true;
                return false;
            }
        }

        return (charCount >= 2 && numCount >= 2);
    }

    public static boolean is_Letter(char ch)
    {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }


    public static boolean is_Numeric(char ch)
    {

        return (ch >= '0' && ch <= '9');
    }

}

