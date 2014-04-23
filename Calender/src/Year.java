import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;


public class Year extends JFrame{
	
	//some variables 
	private int solarYear ,firstWeekDay;
    private int solarMonth;
	public Date now = new Date();
	
	//
    final static String chineseNumber[] = {"正", "二", "三", "四","五", "六", "七", "八", "九", "十", "冬月", "腊月" };
	final String[] Gan = new String[]{"甲","乙","丙","丁","戊","己","庚","辛","壬","癸"};
	final String[] Zhi = new String[]{ "子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};	
	final String[] Shengxiao = new String[]{ "rat","ox", "tiger", "rabbit", "dragon", "snake", "horse", "goat", "monkey", "rooster", "dog", "pig"};
	final String[] WeekName = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	final String[] solar_term_name =new String[] {"小寒", "大寒", "立春",
			           "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑",
			           "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"}; 
	final String[] chineseDay=new String[]{"one","two","three","four","five", "six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen",
            "sixteen","seventeen","eighteen","nineteen","twenty","twenty_one","twenty_two","twenty_three","twenty_four","twenty_five","twenty_six","twenty_seven","twenty_eight","twenty_nine","thirty"};
	final int[] MonthDay = new int[]{0,31,59,90,120,151,181,212,243,273,304,334,365};
	
	//
	private JTextField jtfGetData = new JTextField();
	private JTextArea jtfShowDate = new JTextArea();
	private JButton jbSee =new JButton("See Calendar");
	
	public Year(){
		JPanel jpGet  =new JPanel();
		jpGet.setLayout(new GridLayout(2,1));
		jpGet.add(jtfGetData);
		jpGet.add(new JLabel("Enter month+year(eg.201112) or month(eg.2011)(year should between 1900-2100)"));
		
		
		JPanel jpData = new JPanel();
		jpData.setLayout(new BorderLayout());
		jpData.add(new JLabel ("date") ,BorderLayout.WEST);
		jpData.add(jpGet,BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane(jtfShowDate);
		scroll.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
				scroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 

				JPanel jpShow =new JPanel();
		jpShow.setLayout(new BorderLayout());
		jpShow.add(jpData,BorderLayout.NORTH);
		jpShow.add(scroll,BorderLayout.CENTER);
		JPanel jpButton = new JPanel();
		jpButton.setLayout(new BorderLayout());
		jpButton.add(jbSee,BorderLayout.CENTER);
		jpShow.add(jpButton,BorderLayout.SOUTH);
		this.add(jpShow);
		
		jbSee.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int getData =Integer.parseInt(jtfGetData.getText());
				String s="";

				if((getData/100)< 100){
					if(getData>2100||getData<1900){
						JOptionPane.showMessageDialog(getComponent(0),"please enter a year between 1900-2100");
					}
					else s =showYear(getData);
				}
				else{
					if(getData%100>12||getData%100<0){
						JOptionPane.showMessageDialog(getComponent(0),"please enter a month between 1-12"); 
					}
						else
							s =showMonth(getData);
				}
				jtfShowDate.setText(s);
			}
		});
	}

	public void setCurrentTime(){
		long time = System.currentTimeMillis()/1000;
		int minutes = (int) (time/60);
		int hours = minutes/60;
		int days = hours/24;
		firstWeekDay = (days+4)%7;
		now.setTime(System.currentTimeMillis());
	}
	
	public void setTime(int year ,int month){
		int day=0;
		for(int j=1900;j<year;j++){
			if(isLeapYear(j))
				day +=366;
			else day+=365;
		}
		day= day+ MonthDay[month-1];
		if(month>2){
			if(isLeapYear(year))
				day++;
		}
		firstWeekDay = (day+1)%7;
	}
	
	public double getTermDay (int y , int n){
	   final  int termInfo[] = {
	        0      ,21208 ,42467 ,63836 ,85337 ,107014,
	        128867,150921,173149,195551,218072,240693,
	        263343,285989,308563,331033,353350,375494,
	        397447,419210,440795,462224,483532,504758
	    };
	   final double x_1900_1_6_2_5 = 693966.08680556;
	   return x_1900_1_6_2_5+365.2422*(y-1900)+termInfo[n]/(60*24); 
	}
	
	public int   format_date( double _days ) 
	 { 
	 	int y , m , d , diff;
	 	long days;
	 	
	 	days = (long) (100 * (_days - _days/(3652425L/(3652425L-3652400L)) )); 
	 	y    = (int) (days / 36524);
	 	
	 	days %= 36524; 
	 	m    = (int) (1 + days/3044);		/* [1..12] */ 
	 	d    = (int) (1 + (days%3044)/100);	/* [1..31] */ 
	  
	 	diff =(int) (y*365 + y/4  - y/100 + y/400+ MonthDay[m-1] + d- _days); 

	  
	 	if( diff > 0 && diff >= d )	/* ~0.5% */ 
	 	{ 
	 		if( m == 1 ) 
	 		{ 
	 			--y; 
	 			m = 12; 
	 			d = 31 - ( diff - d ); 
	 		} 
	 		else  
	 		{			 
	 			d = MonthDay[m-1] - ( diff - d ); 
	 			if( --m == 2 ) 
	 				if( ((y&3)==0) && ((y%100)!=0||y%400==0))
	 				d ++;
	 		} 
	 	} 
	 	else 
	 	{ 
	 		if( (d -= diff) > MonthDay[m] )	/* ~1.6% */ 
	 		{ 
	 			if( m == 2 ) 
	 			{ 
	 				if(((y&3)==0) && ((y%100)!=0||y%400==0)) 
	 				{ 
	 					if( d != 29 ) {
	 						m = 3 ;
	 					d -= 29; 
	 					}
	 				} 
	 				else 
	 				{ 
	 					m = 3 ;
	 					d -= 28; 
	 				} 
	 			} 
	 			else 
	 			{ 
	 				d -= MonthDay[m]; 
	 				if( m++ == 12 ) {
	 					++y ;
	 					m = 1; 
	 				}
	 			} 
	 		} 
	 	}
	 	return (d+m*100+y*10000);
	 	}	
	
	public String getZhiGan(int year){
		int r = year%60  -3;
		if(r<0)
		{
			r =r +60;
		}
		String s =Gan[r%10]+ Zhi[r%12] +"       "+"--"+Shengxiao[r%12];
		return s;
	}
	
	public boolean isLeapYear(int year){
		if(year%4==0 ){
			if(year%100==0){
				if(year%400==0)
					return true;
				else return false;
			}
			else return true;
		}
		else return false;
	}
	
	 public int getLunarDay(int year ,int month,int days){
		 int q = (year-1977)/4;
		 int r = (year-1977)%4;
		 double j = (14*q +10.6*(r +1));
		 j += MonthDay[month-1];
		 j +=days;
		 if(isLeapYear(year))
			 j++;
		 double k=(j-(int)(j/29.5)*29.5);
		 if((int)k==0){
			 k=30;
			 return (int)k;
		 }
		 else {
		 if(((int)k*10-10*k)>=5)
			 return (int) k+1;
		 else  return (int) k;
	 }
	 }
	 
	 public int getLunarDate(int year ,int month ,int lunarDay,int solarDay){
		 int nowTime =year*10000+month*100+solarDay;
		 int lunarYear = 0,lunarMonth = 0 ,termNum;
		 termNum =0;
		 int former = format_date(getTermDay(year, termNum));
		 int later = format_date(getTermDay(year, termNum+1));
		 if(former <=nowTime){
			 while (true){
				
				 former = format_date(getTermDay(year, termNum));
				 later = format_date(getTermDay(year, termNum+1));
				 if(former<=nowTime && (later > nowTime))
					 break;
				 else termNum++;
				 if(termNum>=23)
					 break;
			 }
		 }
		 if(termNum<=1){
			 switch(termNum){
			 case 0:{
				 lunarMonth=12;
				 lunarYear =year-1;
				 break;
			 }
			 case 1:{
				 if(lunarDay<13){
					 lunarMonth=1;
					 lunarYear =year;
					 break;
				 }
				 else {
					 lunarMonth =12;
					 lunarYear =year-1;
					 break;
				 }
			 }
			 }
		 }
		 else{				
			 lunarYear =year;
			 if(termNum %2==0){
				 lunarMonth = termNum/2;
			 }
			 else{
				 if(lunarDay<13){
					 lunarMonth = termNum/2+1;
				 }
				 else
					 lunarMonth =termNum/2;
			 }
		 } 
		 return (lunarYear*10000+lunarMonth*100+lunarDay);
	 }

	 public String showMonth(int data){
		 solarYear = data/100;
		 solarMonth = data%100;
		 String s =getHead(solarYear ,solarMonth);
		 s = s+ getBody(solarYear ,solarMonth);
		return s; 
	 }

	 public String showYear(int data){
		 String s="";
		 for(int i=1;i<=12;i++){
			 s+=showMonth(data*100+i);
		 }
		return s;
	 }
	 
	 public String getHead(int year ,int month){
		 String s ="";
		 s ="****************************************"+Integer.toString(year)+"\t"+getZhiGan(year)
				 +chineseNumber[month-1]+""+"****************************************"+"\n";
		 for(int i=0;i<7;i++){
			 s += String.format("%18s",WeekName[i]);
		 }
		 s =s+'\n';
		 return s;
	 }
	 
	 public String getBody(int year ,int month){
		 setTime(year ,month);
		 String s ="";
		 int lDay,lMonth ,lYear,sDay = 0;
		 int monthTotalDay =getTotalNumberOfDaysInMonth(year,month);
		 for(int i=0;i<firstWeekDay ;i++){
			 s += String.format("%20s","");
		 }
		 for(int i=1;i<=monthTotalDay;i++){
			 s =s+String.format("%20s",Integer.toString(i) );
			 if((i+firstWeekDay)%7==0){
				 s+="\n";
				 if(i<7){
					 for(int j=0;j<firstWeekDay;j++){
						 s += String.format("%20s","");
					 }
					 sDay = i+firstWeekDay -6;
				 }
				 else sDay =i-6;
				 while((sDay+firstWeekDay)%7!=0){
					 lDay =getLunarDay(year,month,sDay);
					 int lunarDate =getLunarDate(year,month,lDay,sDay);
					 lYear =lunarDate/10000;
					 lMonth = (lunarDate%10000) /100;
					 lDay =lunarDate%100;
					 String ss =chineseNumber[lMonth-1]+"/"+chineseDay[lDay-1];
					 s +=String.format("%1s",ss);
					 s += String.format("%5s","");

					 sDay++;
				 }
				 lDay =getLunarDay(year,month,sDay);
				 int lunarDate =getLunarDate(year,month,lDay,sDay);
				 lYear =lunarDate/10000;
				 lMonth = (lunarDate%10000) /100;
				 lDay =lunarDate%100;
				 String ss =chineseNumber[lMonth-1]+"/"+chineseDay[lDay-1];
				 s +=String.format("%1s",ss);
				 s += String.format("%5s","");
				 s+="\n";
			 }			 
		}
		 s+="\n";
		 
	  	 while(sDay !=monthTotalDay){
			 lDay =getLunarDay(year,month,sDay);
			 int lunarDate =getLunarDate(year,month,lDay,sDay);
			 lYear =lunarDate/10000;
			 lMonth = (lunarDate%10000) /100;
			 lDay =lunarDate%100;
			 String ss =chineseNumber[lMonth-1]+"‘¬"+chineseDay[lDay-1];
			 s +=String.format("%12s",ss);
			 sDay++;
		 }
		 s+="\n";
		 return s;
	 }
	 public int getTotalNumberOfDaysInMonth(int year,int month){
		 if(month==2){
			 if(isLeapYear(year)){
				 return 29;
			 }
			 else return 28;
		 }
		 else{
			 return MonthDay[month]-MonthDay[month-1];
		 }
	 }
	 
	 
public static void main(String[] args){
	Year frame =new Year();
	
    frame.pack();
    frame.setTitle("calendar");
    frame.setSize(600,400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
	Point newLocation = new Point(middle.x - (frame.getWidth() / 2), 
	                              middle.y - (frame.getHeight() / 2));
	frame.setLocation(newLocation);
    frame.setVisible(true);
}

}
