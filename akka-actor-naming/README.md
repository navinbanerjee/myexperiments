# akka-actor-naming  
### *The effect of instantiating child actor(s) Asynchronously and the child actor name(s) getting reused*  
  
  
#### InvalidActorNameException  
  
*Discounting the easier solution of assigning unique child actor names as that can lead to a spike in the number of children for certain time duration and the number of children crosses the intended threshold*  
  
  
Two simple solutions (search for the text SOLUTION in the code base)  
  
- child instantiation needs to be **synchronous** with `postRestart` : check the `Manager.java` and update the `preRestart` method  
  
- **check for existence** of children or child count on `postRestart` before instantiating asynchronously and reusing the name : check the `Manager.java` and update the `start` method  
  
  
## Compile and Run  
  
`mvn clean compile`  
  
`mvn exec:java -Dexec.mainClass="net.ars.sample.actor.Main"`  
  
`mvn exec:java -Dexec.mainClass="net.ars.sample.actor.Main" -Dexec.args="unique-actor-name"`
  
  