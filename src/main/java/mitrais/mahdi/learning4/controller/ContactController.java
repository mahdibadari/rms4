package mitrais.mahdi.learning4.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.net.HttpURLConnection;
import java.util.Scanner;

import mitrais.mahdi.learning4.service.*;
import org.json.*;
import com.google.gson.*;

import mitrais.mahdi.learning4.model.*;

@Controller
@SessionAttributes("name")
public class ContactController {
    static JdbcTemplate jdbcTemplateObj;
	static SimpleDriverDataSource dataSourceObj;

	// Database Configuration Parameters
	static String DB_USERNAME = "mahdibdr";
	static String DB_PASSWORD = "mitrais123";
    static String DB_URL = "jdbc:mysql://localhost:3306/sonoo";
    
    public static SimpleDriverDataSource getDatabaseConnection()  {
		dataSourceObj = new SimpleDriverDataSource();
		try {			
			dataSourceObj.setDriver(new com.mysql.jdbc.Driver());
			dataSourceObj.setUrl(DB_URL);
			dataSourceObj.setUsername(DB_USERNAME);
			dataSourceObj.setPassword(DB_PASSWORD);
		} catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return dataSourceObj;
    }
    
    @RequestMapping(value="/deleteContact", method = RequestMethod.POST)
    public static void DeleteData(String name){
        jdbcTemplateObj = new JdbcTemplate(getDatabaseConnection());

		if(null != jdbcTemplateObj) {	
			String sqlDeleteQuery = "DELETE FROM contact where name?";
			jdbcTemplateObj.update(sqlDeleteQuery, name);
		} else {
			System.out.print("Application Is Not Able To Bind With The Database! Please Check!");
		}
    }
}
