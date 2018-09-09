Install tcwebcommon to local repository.
```
mvn install:install-file -Dfile=lib/shared/tcwebcommon.jar -DgroupId=tc-components -DartifactId=tcwebcommon -Dversion=1.0.0 -Dpackaging=jar
```

Install shared to local repository.
```
mvn install:install-file -Dfile=lib/shared/shared.jar -DgroupId=tc-components -DartifactId=shared -Dversion=1.0.0 -Dpackaging=jar
```

Install catalog to local repository.
```
mvn install:install-file -Dfile=lib/shared/catalog.jar -DgroupId=tc-components -DartifactId=catalog -Dversion=1.0.0 -Dpackaging=jar
```

Install forums to local repository.
```
mvn install:install-file -Dfile=lib/shared/forums.jar -DgroupId=tc-components -DartifactId=forums -Dversion=1.0.0 -Dpackaging=jar
```

Install security to local repository.
```
mvn install:install-file -Dfile=lib/tcs/security_ejb/security.jar -DgroupId=tc-components -DartifactId=security -Dversion=1.0.0 -Dpackaging=jar
```

Install User to local repository.
```
mvn install:install-file -Dfile=lib/tcs/user_ejb/User.jar -DgroupId=tc-components -DartifactId=user -Dversion=1.0.0 -Dpackaging=jar
```

Install id_generator to local repository.
```
mvn install:install-file -Dfile=lib/tcs/id_generator/3.0.2/id_generator.jar -DgroupId=tc-components -DartifactId=id_generator -Dversion=3.0.2 -Dpackaging=jar
```

Install jivebase to local repository.
```
mvn install:install-file -Dfile=lib/third_party/jive/jivebase.jar -DgroupId=tc-components -DartifactId=jivebase -Dversion=1.0.0 -Dpackaging=jar
```

Install jiveforums to local repository.
```
mvn install:install-file -Dfile=lib/third_party/jive/jiveforums.jar -DgroupId=tc-components -DartifactId=jiveforums -Dversion=1.0.0 -Dpackaging=jar
```
