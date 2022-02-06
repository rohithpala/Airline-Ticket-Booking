<html>

# Objective
The main objective of this project is to book, cancel, print tickets, and enquire on the flights using the 
from and to locations. GUI is used completely in the project. Signup, Login features are also added in the 
project. Passengers can create an account for themselves or can login directly if they have one. Every 
username is unique. This is a very useful project for any Airline reservations.

# Working
The program confirms the user first. Then According to the button clicked the code runs as follows. If 
Enquiry button is clicked, from and to locations are taken and the flights are displayed. If Booking 
Button is clicked, the information is taken and written into a database. If Cancel Button is clicked, the 
phone number and a corresponding reference ID are taken and if confirmed then the ticket gets cancelled. 
If Print Button is pressed, using the phone number and reference ID given a PDF will be generated with the 
passenger’s information. When clicked on Logout button the passenger can logout of their account safely.
Topics Covered: MVC, swing (JFrame, JPanel, JTextField, JPasswordField, JButton), awt, util (Date & 
Objects), io Packages, itextpdf Package, Mobile Number Validation using RegEx, Exception Handling, 
Interfaces, Classes, Objects, Constructors.
<br>
Languages Used: Java

# Introduction
Managing flight tickets is a fun and important task for the travel agencies. Our project helps them to 
manage it in an efficient manner
### Description:
<ol>
<li>When the program gets started, A JFrame will appear on the screen that has A Login and A Signup Button.</li>
<li>The user after validation enters the next stage where he can find 5 buttons namely Logout Button, 
Enquiry, Booking, Cancel and Printing.</li>
<li>If Booking Button is clicked, name, phone number, number of adults, children, and infants, from and to 
locations are asked as input from the user.</li>
<li>After Completing Successful data entry the user is prompted to select a flight based on the from and 
to locations. Then he can print the ticket in the form of a pdf.</li>
<li>If the user clicks on Enquiry button, from and to locations are asked and corresponding flight names 
are shown.</li>
<li>If Cancel Button is Clicked, phone number and corresponding reference ID are taken for validating the 
user and if correct then the ticket gets cancelled, else he will be prompted with corresponding message.</li>
<li>If Print Button is clicked, phone number and corresponding reference ID are taken for validating the 
user and if correct then the ticket gets printed in format of a pdf.</li>
<li>If Logout button is clicked the user is redirected to the HomePage.</li>
<li>Also, Every Page contains a Back Button that when clicked redirects the user to those 5 options pages.</li>
</ol>

# Design
## 3.1 Strategy:
The Goal of this project is to manage the flight tickets using MVC (GUI) in java. This Strategy is used to 
manage records in a fast and efficient manner.
## 3.2 Algorithm:
<ol>
<li>User Logs in or Signs up using the credentials</li>
<li>Login and Signup both, read the file LoginSignupDatabase.txt and for login if the username and </li>
password are present next dialog is opened. For Signup if the username is not present in the file, the 
next dialog box is opened.
<li>In Enquiry, from and to locations are to be chosen from the JComboBoxes. FlightNames.txt file </li>
is opened in a FileInputStream on which a BufferedReader is opened. If the from and to both are present in 
a line, those lines are printed. If no line is found, then a message saying “Sorry! Flights from (from 
location) to (to location) not Available” is shown.
<li>In Booking Process, name, phone number, number of adults, children, infants, from and to </li>
locations are taken. A reference ID is created. After successful registration, the total information is 
written into CustomersDataBase.txt file in csv format and – “Booked Successfully” message is shown 
followed by a Print Ticket Button. If the Print Ticket Button is Clicked a PDF gets generated with the 
passenger and flight information.
<li>In Cancelling, phone number and reference Id are taken and from the CustomersDataBase.txt file </li>
they are checked. If the user with the given information is present, then the particular string is removed 
from the file.
<li>Clicking Printing Button generated a PDF with the user and flight information</li>
<li>By Pressing the Logout Button the user is redirected to the HomePage</li>
</ol>
</html>