# VikasTestproject
Vikas Test Project Instructions for running the project
For running the project navigate to the VikasTestproject folder location via terminal/command prompt and then use the command as per OS-Browser compatibility. Change the URL as per environment.

**For running the project on linux or mac, make the chrome driver(located in \VikasTestproject\lib) executable by using command [chmod +x chromedriver]
**Once the execution is completed reports can be found at VikasTestproject\reports\testng-xslt\index.html and failure screen shots at VikasTestproject\reports\screenshots

Please change the URL of environment under test in the commands below and in following property file[Only if not running on cmsqc]:
user.dir//src//Configuration//credential.properties   [environmentURL=https://dropbox.com]


Change the parameter in the command as per OS-Browser compatibility:

For best performance, it is advisable to delete complete browser history(From beginning of time) and temporary files from the system before running the regression scripts. 


Parameters: 
For OS: 
Windows = win
Mac = mac
Linux 32 bit = linux32
Linux 63 bit = linux64

For browsers:
Chrome =  chrome
Firefox = ff
Internet Explorer = ie 
Android = android [For android browser, OS parameter is mandatory but it can take any value from above defined OS. Changes required(IP) in BaseClass.java on run]

For running project on chrome and OS windows: [Change the parameters according to above legend]

1. Running Login Test Cases
   ant runLogin -DantUrl=https://dropbox.com -DantBrwsr=chrome -DantOS=win 


