import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];

  map: any;
  currentLat: any;
  currentLong: any;
  geolocationPosition: any;

  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    //At the begining of every search we want to make sure that the array is reset so that there is no pile on of the users searches.
    this.venueList = [];
    this.recipeList = [];
    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    /* The following if else statement will check weither or not the user has allowed the use of their current location.
    As some users will preffer to opt out of the webpage using their current location the following code will update the 
    Google maps hyperlink accourdingly. */
    if(typeof(this.currentLat) == 'undefined' || typeof(this.currentLong) == 'undefined'){
      this.map = "http://maps.google.com/maps?daddr=";
    }
    else{
      this.map = "http://maps.google.com/maps?saddr="+this.currentLat+","+this.currentLong+"&daddr=";
    }

    /* The following the code for retreaving and storing the recipes from the Edamam api. */

    if (this.recipeValue !== null) {
      //Uses fetch to get the desired data from the api using the given value.
      fetch('https://api.edamam.com/api/recipes/v2?type=public&q='+this.recipeValue+'&app_id=3f8a3fba&app_key=24cae7073d28e826ddfd91842c8b73b8')
        .then(response => response.json())
        /* After converting the data to json, the program will then iterate ten times through our json structior and 
        -add onto the array the desired data, which in this case it is the name, url, and image of the recipe. 
        For some reason each element in the array would have two elements of its own so using [0] we save only the correct data. */
        .then(data =>  { for (let i = 0; i < 10; i++) {
          this.recipeList.push(Object.keys(data.hits[i]).map(function (r) {
            var j = data.hits[i][r];
            return {name: j.label, url: j.url, icon: j.image}
          })[0])
        }}) 
        .catch(err => console.error(err));
    }

    /* The following the code for retreaving and storeing the recipes from the Foursquare api. */

    if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') {
      
      //neccesary for fetch request for this api
       const options = {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          Authorization: 'fsq3gJ9Dplx9i0kFnYSKJOZTiSowkQXB0lb4wfm5S8Vq4tM='
        }
      };
      
      fetch('https://api.foursquare.com/v3/places/search?query='+this.recipeValue+'&near='+this.placeValue, options)
        .then(response => response.json())
        //Just like the Edamam api, we store it as json then add elements to our array.
        .then(data =>  { for (let i = 0; i < 10; i++) {
          this.venueList.push(Object.keys(data.results).map(function (v) {
            var j = data.results[v];
            return {name: j.name, location: this.map+j.location.address}
          }, this)[i])
        }})
        .catch(err => console.error(err));
    }
  }

}
