package com.nure.kuib19;

import com.nure.kuib19.pages.HomePage;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest{

    @Test
    public void test(){
        HomePage.clickCity();
    }
}