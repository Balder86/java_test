JSF
===

Caveat: javaee-api Maven scope
------------------------------
Symptom:
    ```
    Caused by: java.lang.ClassNotFoundException: javax.faces.webapp.FacesServlet
        at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1333)
        at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1167)
        ... 54 more

    ```

Cause:
    The following Maven artefact must be set to scope=compile instead of
    the usual scope=provided as plain Servlet Containers do not provide
    the included classes. At least javax.faces.webapp.FacesServlet is required
    at runtime though.

Solution:
    ```
    <dependency>
        <!-- Java EE: API classes for all included standards -->
        <groupId>javax</groupId>
        <artifactId>javaee-api</artifactId>
        <version>7.0</version>
        <scope>compile</scope> <!-- Needed at runtime and not provided by plain Tomcat -->
    </dependency>
    ```


JSF API is needed at runtime
----------------------------
Problem:
```
    Caused by: java.util.MissingResourceException: Can't find javax.faces.LogStrings bundle
        at java.util.logging.Logger.setupResourceInfo(Logger.java:1946)
        at java.util.logging.Logger.<init>(Logger.java:380)
        at java.util.logging.LogManager.demandLogger(LogManager.java:554)
        at java.util.logging.Logger.demandLogger(Logger.java:455)
        at java.util.logging.Logger.getLogger(Logger.java:553)
        at javax.faces.FactoryFinder.<clinit>(FactoryFinder.java:334)
        ... 50 more
```

Cause:
The LogStrings.properties is in the javax.javaee-api-7.0-sources.jar Maven 
artifact but not in javax.javaee-api-7.0.jar. Don't know why.

Solution:
Add jsf-api as runtime dependency:
```
    <dependency>
        <!-- JSF: Implementation -->
        <groupId>com.sun.faces</groupId>
        <artifactId>jsf-api</artifactId>
        <version>2.2.14</version>
        <scope>runtime</scope>
    </dependency> 
```

JSON
====

"Jackson" JSON Implementation
-----------------------------

* com.fasterxml.jackson.core:jackson-databind
Defines ObjectMapper which I use for Debugging output.

* com.fasterxml.jackson.core:jackson-annotations
Apparently needed if Jackson JAX-RS is used and jackson-databind is in the classpath.
Else the following Exception is thrown:
```
javax.servlet.ServletException: org.glassfish.jersey.server.ContainerException: java.lang.NoClassDefFoundError: com/fasterxml/jackson/annotation/JsonInclude$Value
	org.glassfish.jersey.servlet.WebComponent.serviceImpl(WebComponent.java:489)
	org.glassfish.jersey.servlet.WebComponent.service(WebComponent.java:427)
	org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:388)
	org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:341)
	org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:228)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
```
