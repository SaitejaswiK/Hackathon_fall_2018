# Health Inspector
<b>Personal Health Care Companion</b>

<ul>
   <li><img width=300 height=500 src='7.jpeg'/>
 
 <img width=300 height=500 src='6.jpeg'/>
<img width=300 height=500 src='5.jpeg'/></li>


<li><img width=300 height=500 src='4.jpeg'/>
<img width=300 height=500 src='3.jpeg'/>

<img width=300 height=500 src='2.jpeg'/>


<img width=300 height=500 src='1.jpeg'/></li>







</ul>

	
<br/>
<br/>
Health Inspector
<br/>
<br/>
Setup:
<br/>
Android Studio:
<br/>	
	Open Android Studio > Build > Edit Build Types > Signing > 
<br/>	
<ol>
  <li>Key Alias: key0</li>
	<li>Key Password: password</li>
 <li>Store File: choose release.jks from app folder</li>
 <li>Store password: password</li>
 </ol>
 <br/>	
<br/>	
In bottom left, choose build variants as Release
<br/>	
Run the application
<br/>	
<br/>	
<br/>	
<b>Libraries Used / Dependencies:</b>

Firebase Auth UI for social login.
<br/>	
Retrofit, OkHTTP for making network calls
<br/>	
GSON to parse JSON response to java object
<br/>	
Google Maps API for location
<br/>	
<br/>	
<br/>	
<b>Authentication:</b>
<br/>	
•	Firebase Login UI Auth for social Login (Mobile, Email, Google Login)<br/>	
•	Fetching SHA-1 for release.jks (check certificate.txt) and Substitute that SHA-1 value in firebase console for Authentication<br/>	
•	Firebase SignOut<br/>	
<br/>	
<br/>	
<b>Doctor Search with Better Doctors API:</b>
	
•	Drop down (Spinner) to specify specialization in doctor search query<br/>	
•	Making REST Call with lat,lng, limit=10 and specialization<br/>	
<br/>		

@GET
https://api.betterdoctor.com/2016-03-01/doctors?location=37.773%2C-122.413%2C100&user_location=37.773%2C-122.413&skip=0&limit=10&user_key=76a2878a9e8d28dcd556ba0c53461174&specialty_uid=pediatrician
<br/>	
And parsing it in Home Activity with RecyclerView
	

<br/>	
<b>Calculating Meals for given target calories using Spoonacular API:</b>
<br/>	
•	Taking Calories per day input through EditText<br/>	
•	Making REST Call with target calories per day <br/>	
<br/>	
@GET
https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/mealplans/generate?timeFrame=day&targetCalories=2000
+<br/>	
Pass Keys in header<br/>	
X-Mashape-Key:  1Dnz7LuzBVmshia88a9IKqmf7n82p1v8KSGjsnP7R2gBashGJA (key)<br/>	
X-Mashape-Host: spoonacular-recipe-food-nutrition-v1.p.mashape.com (key)<br/>	
<br/>	
And parsing it in MyDiet Activity with Recycler View and in Nutrients View
<br/>	
<br/>	
<br/>	
<b>BMI Activity</b>
<br/>	
<br/>	
<br/>	
<img width=500 height=450 src='bmi.png'/>
<br/>
Calculating BMI taking weight and height as inputs and using above table info.

Video link: https://youtu.be/6ON6F2VFWRA

PPT Link: https://drive.google.com/file/d/1Za9Cl7Vk9PdqqpZM1n_KIl3AevRPsQfO/view?usp=sharing
