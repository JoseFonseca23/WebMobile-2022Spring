import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'TO DO LIST FOR 2022'
  // define list of items
  public items: Array<{ taskName: string, completed: Boolean, countDown: any}> = [];
  done;

ngOnInit() {
    this.items = [
      {
        taskName: 'First',
        completed: false,
        countDown: this.createCounrdown(0,"10/23/2022")
      },
      {
        taskName: 'Second',
        completed: true,
        countDown: this.createCounrdown(1,"12/31/2022")
      },
    ]
  }

  // Write code to push new item
  submitNewItem(task, date) {
    this.items.push({taskName: task,
    completed: false,
    countDown: 0});
    this.createCounrdown(this.items.length-1, date);
  }

  // Write code to complete item
  completeItem(id) {
    this.items[id].completed=true;

  }

  // Write code to delete item
  deleteItem(id) {
    this.items = this.items.filter((v, i) => i != id);
  }

  date =  ""
  timeSet = false;
  countDownDate = new Date().getTime();

  demo = "0d 0h 0min 0sec"
  timer:any;
  
  x = setInterval(()=>{
    let n = new Date().getTime();
    let distance = this.countDownDate - n;
    let days = Math.floor(distance/(1000*60*60*24));
    let hours = Math.floor((distance % (1000*60*60*24)) / (1000*60*60));
    let min = Math.floor((distance % (1000*60*60)) / (1000*60));
    let sec = Math.floor((distance % (1000*60)) / 1000);
    this.timer = days + "d " + hours + "h " + min + "min " + sec + "sec ";
  })

  submitTime(date){
    this.countDownDate = new Date(date).getTime();
    this.timeSet = true;
  }

  createCounrdown(index, goal){
    //create the date that will be used to reach
    let event = new Date(goal);
    let date = event.getTime();
    if(isNaN(event.valueOf())){
      console.log("Invalid date")
    }
    else{
    //set an interval that will update those values as a count down.
      var interval = setInterval(()=>{
        if(!this.items[index]) {
          clearInterval(interval);
        }
        let n = new Date().getTime();
        let distance = date - n;
        let days = Math.floor(distance/(1000*60*60*24));
        let hours = Math.floor((distance % (1000*60*60*24)) / (1000*60*60));
        let min = Math.floor((distance % (1000*60*60)) / (1000*60));
        let sec = Math.floor((distance % (1000*60)) / 1000);
        if(days<= 0 && hours<= 0 && min<=0 && sec<=0){
          this.items[index].countDown = (event.toDateString()+"- Time left: 0d 0h 0min 0sec")
          clearInterval(interval);
        }
        else{
          this.items[index].countDown = (event.toDateString()+"- Time left: "+days + "d " + hours + "h " + min + "min " + sec + "sec ");
        }
      })
    }
  }
}
