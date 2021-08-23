## Hand-in report DAT250

### Maven
I had no trouble downloading and installing Maven on my machine.
Steps I took:
   - [Maven download page](https://maven.apache.org/download.cgi)
   - Download binary as zip, move to users/%my_user_name%
   - Windows, edit environment variables, add ``/bin`` directory to PATH
   - Exit/restart shell
   - Run ``mvn -v``
  
### JDK
I already had a recent version of ``OpenJDK`` installed.

### IDE
I already had an ``IntelliJ`` installation with a student license.

### Git
I already had git installed on my machine.
```terminal
$ git --version
git version 2.28.0.windows.1
```

### Heroku

I followed the tutorial up until the point where it asked me to either use a template Java project provided by Heroku themselves, or continue the guide with my own application and chose the latter.
I then continued using my own application.

The application exposes one endpoint, ``/api/v1/hello``. See results of hitting this endpoint after deployment at the end of the document. The code:

```java
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class SimpleController {
    @GetMapping(value = "/hello")
        public ResponseEntity<Hello> sayHello() {
            return new ResponseEntity<>(
                    Hello.builder()
                        .message("200 OK")
                        .statusCode(HttpStatus.OK.value())
                        .build(), HttpStatus.OK);
        }
}
```

I had trouble setting up the right Java version at first. 
Build outputs of ``heroku push master`` failed with
```terminal
Fatal error compiling: invalid target release 16
```

To fix this, I created a ``system.properties`` file, specifying ``java.runtime.version=16``. I then committed and pushed this new file to git, followed by a new ``heroko push master``.

#### Scaling
I ensured that atleast one ``dyno`` served my application:
```terminal
$ heroku ps:scale web=1
Scaling dynos... done, now running web at 1:Free
```

Then, scaling down to 0 dynos:
```terminal
heroku ps:scale web=0
Scaling dynos... done, now running web at 0:Free
```

Using CURL to health-check:
```terminal
$ curl https://secret-sands-21120.herokuapp.com/api/v1/hello -verbose
```

To find the Heroku HTTP routing stack returns a ``503 Service Unavailable`` header after scaling to 0 dynos.

I skipped the part of adding dependencies, as I had already added Spring Boot's starter dependencies to my ``pom.xml``.

#### Config variables

The new code:
```java
@EventListener(ApplicationStartedEvent.class)
public void doOnApplicationStarted() {
    var configVar = System.getenv("helloMessage");
    if (configVar != null) {
        log.info("Found conifigVar: {}", configVar);
    }
}
```
This method will run when the Spring Boot application has started.

The Heroku part:
I added a config variable using ``heroku config:set helloMessage="hello, world!"``

```terminal
$ heroku config:set helloMessage="hello, world!"
Setting helloMessage and restarting ¤ secret-sands-21120... done, v5
helloMessage: hello, world!
```

I then checked the newest logs using ``heroku logs --tail``:
```terminal
n.h.d.herokudemo.HerokuDemoApplication   : Found configVar: hello, world!
```

#### Provisioning add-ons
I skipped the part of using ``Papertail``.