## Hand-in report DAT250

### Maven
I had no trouble downloading and installing Maven on my machine.
Steps I took:
   - [Maven download page](https://maven.apache.org/download.cgi)
   - Download binary as zip, move to users/%my_user_name%
   - Windows, edit environment variables, add ``/bin`` directory to PATH
   - Exit/restart shell
   - Run ``mvn -v``
```terminal
$ mvn -v

```
  
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

I had trouble setting up the right Java version at first. 
Build outputs of ``heroku push master`` failed with
```terminal
Fatal error compiling: invalid target release 16
```

To fix this, I created a ``system.properties`` file, specifying ``java.runtime.version=16``. I then commited and pushed this new file to git, followed by a new ``heroko push master``.

Note: I did not use the template Java project provided by the heroku docs, but instead created my own Spring Boot application.

Using CURL:
```terminal
$ curl https://secret-sands-21120.herokuapp.com/api/v1/hello | jq
{
  "message": "200 OK",
  "statusCode": 200
}
```
