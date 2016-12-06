# Android-Development
Android Development Course Projects

RainOrShine
Basic weather application that utilizes openweathermap API to allow users to search for cities and receive weather.

Android Widgets: Floating Action Button, SnackBar, LinearLayoutManager, RecyclerView, NavigationView, DrawerLayout, Toolbar, Toast
Application: DialogueFragment, FragmentManager
External: Retrofit, Glide, GSON, OpenWeatherMap API

User Interaction:
The application has two activities, the first Activity holds the list of cities that the user stores in the application and when a city 
is selected it’s weather details is displayed on another Activity.
The city list Activity has RecyclerView and supports adding and removing cities. Cities can be added by clicking on a FloatingActionButton 
that shows a DialogFragment where the user can enter the city name.
The city list Activity has a NavigationDrawer with the following menus:
o Add city
o About (displays the author of the application on a Toast message).
The details Activity appears when a city name is clicked. It holds a ViewPager with two fragments. The first fragment displays the main 
weather information with an icon/image that refers to the weather, while the second fragment shows more details
------
AndWallet
Simple wallet application that allows users to add income and add payments and calculates sum of their wallet
Graphics: Color
Widget: Button, ImageView, TextView, EditText
View: LayoutInflater
External: ButterKnife
------
ShoppingList
List of items with category, icon, name, description, price, bought/not
Android Widgets: Intents, Spinner, ArrayAdapter
RDBMS: SugarORM
Add-“New Item” in Toolbar
Delete-can delete individually or "Delete All" in Toolbar

