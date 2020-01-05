# Athena

This is a showcase project to learn/demonstrate how to effectively use `Spring Framework` and `DDD` for a fairly complex domain like a flash card app.

## The Domain
A simple flash card app to learn and memorise anything you want.

#### Why Flash Cards?
Flash cards are an age-old method for improving students' abilities to recall information and understand concepts. There's a reason flash card are just as popular today as they ever were — they do their job, and they do it well.

This means there is potentially value in an actual follow up to extend functionalities and make this app an OS project. Why not?


## Defining Bounded Context

This is the Context Map deriving from a solo _Event Storming_ - I used [Miro](https://miro.com/) for this, which I strongly recommend for any virtual ES session. 

Yes, it's not optimal, but in my opinion it is still better than just start coding or arbitrarily define BCs.

### Event Storming - Flow
Event Storming Flow -> [TBD]

### Context Map

Context Map -> https://miro.com/app/board/o9J_kvtFpcU=/

## The tech stack
[TBD]

## How to run the application
The only requirement you'll need is [Docker](https://docs.docker.com/install/).

From the root of the project run `make run` and, when completed, visit `http://localhost:8088/actuator/health`

If you see a `{"status":"UP"}` response, you can start using the API.

## How to run tests
TBD

## Is there any frontend using this API?
TBD

## Demoing the API
TBD

## Kanban Board
I used a Trello board [here](https://trello.com/b/ZXlyA2dN/athena) to manage and keep tracks of the things to do. I could also have used Github tbh.