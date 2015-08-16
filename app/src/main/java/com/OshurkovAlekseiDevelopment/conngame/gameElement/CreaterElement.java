package com.OshurkovAlekseiDevelopment.conngame.gameElement;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.OshurkovAlekseiDevelopment.conngame.gameElement.Element;
import com.OshurkovAlekseiDevelopment.conngame.core.ParameterApplication;

public class CreaterElement {
	
	private final int row = ParameterApplication.defaultRow;
	private final int colums = ParameterApplication.defaultColums;
	int COUNT = 0;
	
	public Element[][] fullingSquare(Context context, int limit){
		
		Element[][] field = new Element[row][colums];		
		Resources res = context.getResources();
		int res1 = 0;
		
		for (int i = 0; i < colums; i++){
			for (int j = 0; j < row; j++){
				
				if(i == 0 || j == 0 || j == (colums - 1) || i == (row - 1))
				{
					field[i][j] = new Element(new ColorDrawable(Color.TRANSPARENT), 7);
					field[i][j].isActive = false;
				}
				else
				{
					if(field[i][j] == null)
					{
						Random rnd = new Random();		    				    		
			    		int rndElement = rnd.nextInt(limit);
			    		int tmpx = 1, tmpy = 1;		    		
			    		boolean che = true;
			    		
			    		switch(rndElement){
			    		  case 0:
			    			  res1 = R.drawable.hero1;		    			    			  
			    			   break;
			    		  case 1:
			    			  res1 = R.drawable.hero2;		    			    			  
			    			   break;
			    		  case 2:
			    			  res1 = R.drawable.hero3;		    				    			 
			    			   break;
			    		  case 3:
			    			  res1 = R.drawable.hero4;		    		
			    			   break;
			    		  case 4:
			    			  res1 = R.drawable.hero5;		    		
			    			   break;
			    		  case 5:
			    			  res1 = R.drawable.hero6;		    			
			    			   break;
			    		  case 6:
			    			  res1 = R.drawable.hero7;		    			
			    			   break;
			    		  case 7:
			    			  res1 = R.drawable.hero8;		    			
			    			   break;
			    		  case 8:
			    			  res1 = R.drawable.hero9;		    			
			    			   break;
			    		  case 9:
			    			  res1 = R.drawable.hero10;		    			
			    			   break;
			    		  case 10:
			    			  res1 = R.drawable.hero11;		    			
			    			   break;
			    		  case 11:
			    			  res1 = R.drawable.hero12;		    			
			    			   break;
			    		  case 12:
			    			  res1 = R.drawable.hero13;		    			
			    			   break;
			    		  case 13:
			    			  res1 = R.drawable.hero14;		    			
			    			   break;
			    		  case 14:
			    			  res1 = R.drawable.hero15;		    			
			    			   break;
			    		  case 15:
			    			  res1 = R.drawable.hero16;		    			
			    			   break;
			    		  case 16:
			    			  res1 = R.drawable.hero17;		    			
			    			   break;
			    		  }
			    		
			    		field[i][j] = new Element(res.getDrawable(res1), rndElement);
			    		
		    			while(che){
		    				   if(field[tmpx][tmpy] == null )
		    				   {
		    					   che = false;
		    					   field[tmpx][tmpy] = new Element(res.getDrawable(res1), rndElement);
		    					}
		    				   else
		    				   {
		    					   tmpx = rnd.nextInt((colums - 1) - 1) + 1; tmpy = rnd.nextInt((row - 1) - 1) + 1;
		    					}
		    			}
		    			COUNT++;
					}
				}
			}
		}
		return field;
	}
}
