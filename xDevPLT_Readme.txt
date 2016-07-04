How to create xDevPLT project based on Appfuse-2.2.1-SNAPSHOT basic-struts2 project/Application
(Platform: JDK1.7+, Maven3.0.4, PostgreSQL9.1+)

0.0.1- : iRestPOS basic version
0.1.0- : iRestPOS development version
1.0.0- : iRestPOS product version

[ENV setup]
1. jdk-7u10-windows-i586.exe: JAVA_HOME=C:\wang\dev\java\jdk1.7.0_10
2. apache-maven-3.0.4-bin.zip: C:\wang\dev\web\apache-maven-3.0.4
3. eclipse-jee-indigo-SR2-win32.zip: C:\wang\dev\web\eclipse
4. postgresql-9.1.1-1-windows.exe: C:\wang\dev\web\PostgreSQL\9.1
5. System param set:
  JAVA_HOME=C:\wang\dev\java\jdk1.7.0_10
  M2_HOME=C:\wang\dev\web\apache-maven-3.0.4
  M2=%M2_HOME%\bin
  Path=%JAVA_HOME%\bin;%M2%;...

[xdevplt_2.2.1_org_20121228] - Appfuse org version
1. Install Maven 3.0.4, and add localRepository dir in C:\wang\dev\web\apache-maven-3.0.4\conf\settings.xml file.
    ...
    <localRepository>C:\wang\dev\web\appfuse-repo\repository</localRepository> 
    ...

2. Go to source dir(D:\wang\dev\web) and run the command to create xdevplt app with Appfuse-2.2.1-SNAPSHOT(struts2/non multi-module).
   mvn archetype:generate -B -DarchetypeGroupId=org.appfuse.archetypes -DarchetypeArtifactId=appfuse-basic-struts-archetype -DarchetypeVersion=2.2.1 -DgroupId=com.xj -DartifactId=xdevplt -DarchetypeRepository=http://oss.sonatype.org/content/repositories/appfuse

3. Go to project dir(C:\wang\dev\web\xdevplt) and get all source.
   mvn appfuse:full-source

  * (Option) If 'mvn appfuse:full-source' happens error, then edit pom.xml file to change repo location.
    ...
    <artifactId>appfuse-maven-plugin</artifactId>
        <version>${appfuse.version}</version>
        <configuration>
            <genericCore>${amp.genericCore}</genericCore>
            <fullSource>${amp.fullSource}</fullSource>
            <trunk>https://svn.java.net/svn/appfuse~svn/</trunk> <!-- Added by wx Ver0.0.1 for 'mvn appfuse:full-source'. -->
        </configuration>
    ...

4. Edit pom.xml to change DB from MySQL to PostgreSQL.
    ...
        <!-- Database settings for PostgreSQL --> <!-- Modified by wx Ver0.0.1 for PostgreSQL. -->
        <dbunit.operation.type>CLEAN_INSERT</dbunit.operation.type>
        <hibernate.dialect>org.hibernate.dialect.PostgreSQLDialect</hibernate.dialect>
        <jdbc.groupId>postgresql</jdbc.groupId>
        <jdbc.artifactId>postgresql</jdbc.artifactId>
        <jdbc.version>9.1-901.jdbc4</jdbc.version>
        <jdbc.driverClassName>org.postgresql.Driver</jdbc.driverClassName>
        <jdbc.url>jdbc:postgresql://localhost:5432/${db.name}</jdbc.url>
        <jdbc.username>postgres</jdbc.username>
        <jdbc.password>abcd1234</jdbc.password>
    ...
    Or you can run 'mvn -Ppostgresql' to use PostgreSQL directly.

5. Build and test App: 
  1) Create xdevplt db in PostgreSQL by pgAdmin.
  2) In project dir, run 'mvn' to build app.
   * Rebuild App: Run 'mvn clean', then run 'mvn' again.

6. Run App with Jetty or Tomcat: Run 'mvn jetty:run', or run 'mvn tomcat:run', then view at http://localhost:8080.

7. Use Eclipse to develop this project.
  1) Edit pom.xml file, change maven-eclipse-plugin to 2.6 version.
            <plugin> <!-- Added by wx Ver0.0.1 for 'mvn eclipse:eclipse'. -->
                <artifactId>maven-eclipse-plugin</artifactId>
                <version>2.6</version>
                <configuration>
                    <additionalProjectnatures>
                        <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
                    </additionalProjectnatures>
                    <additionalBuildcommands>
                        <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
                    </additionalBuildcommands>
                    <downloadSources>true</downloadSources>
                    <downloadJavadocs>true</downloadJavadocs>
                    <wtpversion>1.5</wtpversion>
                </configuration>
            </plugin>
  2) In project dir, run 'mvn eclipse:eclipse'.

8. To install the m2eclipse plugin in the Eclipse IDE.
  1) Select Help > Install New Software. This should display the "Install" dialog.
  2) Paste the Update Site URL(m2e Ver0.12) "http://m2eclipse.sonatype.org/sites/m2e" into the field named "Work with:" and press Enter. 
  3) Choose the component listed under m2eclipse: "Maven Integration for Eclipse (Required)".
  4) Click Next and Eclipse will then check to see if there are any issues which would prevent a successful installation.
  5) Click Next and agree to the terms of the Eclipse Public License v1.0.
  6) Click Finish to begin the installation process. Eclipse will then download and install the necessary components.
  7) Once the installation process is finished, Eclipse will ask you if you want to restart the IDE. Sonatype strongly recommends that you restart your IDE after installing m2eclipse.
  8) Set maven home in Preferences->Maven->Installations, Add the External Maven "C:\wang\dev\web\apache-maven-3.0.4". In User Settings, update the local settings.xml.
  9) Restart Eclipse, then M2_REPO will be auto set to C:\wang\dev\web\appfuse-repo\repository in windows->Pereferences->Java->Build Path->Classpath Variable.

9. Import project into Eclipse.
  1) Import xdevplt project by File->import->Existing project.
  2) After import, right click xdevplt project, and select Maven->Enable Dependency Management.
     (Otherwise @WebService link error will be happened, or change JRE System Library to JavaSE-1.6.)

10. Run maven command and debug App in the Eclipse IDE.
  1) Right click xdevplt project, select Run As->Run configurations..., right click Maven Build->New, 
     input 'jetty_run' for Name, '${workspace_loc:/${project_name}}' for Base directory, 'jetty:run' for Goals, 
     and select 'Offline', 'Debug Output', 'Skip Tests' check box, then click Apply button.
     * If you want to change Jetty port to 8081, then input 'jetty:run -Djetty.port=8081' for Goals.
     * You need to run "mvn clean" before "mvn jetty:run", because of index writing error.
  2) Set a breakpoint in UserAction, and right click xdevplt project, select Run As->Maven build to start jetty sever.  
  3) View App in a Browser at http://localhost:8080 by Admin/admin, when step on the breakpoint, if can not find source, 
     set project directory for source search and select Subfolders check box.
  4) Stop Jetty server: Click Terminate icon in Console tab window.

11. Normal Maven commands and parameters. (Ref: http://appfuse.org/display/APF/Maven+2)
  (Option: Modify 'ne' to '!=' in header.jsp and default.jsp file at frist.)
  1) Complie source code: mvn Complie, mvn test-compile
  2) Run test: mvn test (Need modify UserActionTest.java for test failure)
               mvn integration-test, mvn test -Dtest=PersonActionTest
  3) Package App: mvn package
  4) Run App: mvn jetty:run, mvn jetty:run-war, mvn tomcat:run, mvn cargo:start -Dcargo.wait=true, mvn cargo:stop
  5) Build clean: mvn clean
  6) Create dev site: mvn site (Need set MAVEN_OPTS=-Xmx512m -XX:MaxPermSize=256m in env param)
  7) Insert default data into DB: mvn dbunit:operation
  8) Export data to target/dbunit/ from DB: mvn dbunit:export -Ddest=sample-data.xml
  9) (Option) Add JDK params to avoid less mem error for Jetty:run in Run configrations->JRE->VM arguments: 
     -XX:PermSize=1024M -XX:MaxPermSize=1024M -XX:+CMSClassUnloadingEnabled -verbose:gc -XX:+PrintGCDetails

[xdevplt_0.0.1] - xdevplt base1 version (Basic modification)
1. (Option) Change JRE System Library to JavaSE-1.6 for WebService lib in Project->Properties->Java Build Path->Libraries.

2. (Tested) Modify pom.xml file for displaytag and errors Chinese msg and backup i18n files in bak folder.
    ...
                        <configuration>
                            <encoding>UTF8</encoding>
                            <includes>
                                <include>ApplicationResources_zh*.properties</include>
                                <include>ApplicationResources_ko*.properties</include>
                                <include>displaytag_zh*.properties</include>
                                <include>errors_zh*.properties</include>
                                <include>errors_ko*.properties</include>
                            </includes>
                            <workDir>src/main/resources</workDir> <!-- Added by wx Ver0.0.1. -->
                        </configuration>
    ...
 
3. (Option) Modify pom.xml to use Tomcat.
            ...
            <plugin> <!-- Added by wx Ver0.0.1. -->
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>tomcat-maven-plugin</artifactId>
                <configuration>
                    <path>/</path>
                </configuration>
            </plugin>
            ...

4. (Option) Modify pom.xml for switching skip tests and skip drop data in DB.
        <skipTests>false</skipTests>  <!-- Added by wx Ver0.0.1. -->

5. Modify language swicth and Edit profile edit functions.
  1) Modify language swicth in default.jsp.
  		<span class="left"><fmt:message key="webapp.version"/>
            <%-- Start: Added by wx Ver0.0.1. --%>
            <c:if test="${pageContext.request.locale.language != 'en'}">
                | <a href="<c:url value='/?locale=en'/>">English</a> 
            </c:if>
            <c:if test="${pageContext.request.locale.language != 'zh'}">
                | <a href="<c:url value='/?locale=zh'/>">Chinese</a> 
            </c:if>
            <%-- End: Added by wx Ver0.0.1. --%>
            <c:if test="${pageContext.request.remoteUser != null}">
            | <fmt:message key="user.status"/> ${pageContext.request.remoteUser}
            </c:if>
        </span>
  2) Add Edit profile at foot in default.jsp.
            <c:if test="${pageContext.request.remoteUser != null}">
            | <fmt:message key="user.status"/> ${pageContext.request.remoteUser}
            <%-- Start: Added by wx Ver0.0.1. --%>
            | <a href="<c:url value='/editProfile'/>"><fmt:message key="menu.user"/></a>
            | <a href="<c:url value='/logout'/>"><fmt:message key="user.logout"/></a>
            <%-- End: Added by wx Ver0.0.1. --%>
            </c:if>
  3) Delete Edit profile menu from menu.jsp.
      <%-- <menu:displayMenu name="UserMenu"/> Deleted by wx Ver0.0.1. --%>
     Switch userForm.jsp to AdminMenu and MainMenu.
    <%-- <meta name="menu" content="UserMenu"/> --%>
    <%-- Start: Modified by wx Ver0.0.1. --%>
    <s:if test="#parameters.from[0] == 'list'">
        <meta name="menu" content="AdminMenu"/>
    </s:if>
    <s:else>
        <meta name="menu" content="MainMenu"/>
    </s:else>
    <%-- End: Modified by wx Ver0.0.1. --%>
  4) Modify webapp and company information in ApplicationResources.properties and pom.xml.
    <version>0.0.1</version>
    <name>Xuji Restaurant POS</name>
    <url>http://www.xujis.com</url>

    (Option) webapp.name=xDevPLT
    webapp.tagline=Xuji Restaurant POS.
    company.name=Xujisoft
    company.url=http://www.xujis.com

6. Modify hibernate.cfg.xml for offline.
<!-- NOTE: If you're working offline, you might have to change the DOCTYPE to the following: -->
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
    "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<!-- <!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
    "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd"> --> <!-- Modified by wx Ver0.0.1. -->

7. (Tested) Modify web.xml and security.xml for only one concurrent session.
    (web.xml)
    <listener> <!-- Added by wx Ver0.0.1 for concurrent session. -->
        <listener-class>org.springframework.security.web.session.HttpSessionEventPublisher</listener-class>
    </listener>

    (security.xml)
    <http auto-config="true">
	...
        <session-management> <!-- Added by wx Ver0.0.1 for concurrent session. -->
            <concurrency-control max-sessions="1"/>
        </session-management>
    </http>

8. Modify applicationContext-dao.xml for turning batching off for better error messages under PostgreSQL.
    <prop key="hibernate.jdbc.batch_size">0</prop> <!-- Added by wx Ver0.0.1. -->

9. (Option) Modify UserActionTest.java and UserDaoTest.java for 'mvn test' failure.
    UserActionTest.java:
        //assertEquals(originalVersionNumber, user.getVersion());
        //assertTrue(action.hasActionErrors());
    UserDaoTest.java: delete testUpdateUser().

10. Modify for passwordHint error.
    //mailEngine.send(mailMessage); // Deleted by wx Ver0.0.1 for send email error.

11. (Option) Modify App information in sample-data.xml, mail.properties and others.
    Web site address: raibledesigns.com -> xujis.com, appfuse.org -> xujis.com
    Email address: xdevplt@xujis.com, xdevplt_admin@xujis.com, xdevplt_user@xujis.com
    Application name: Appfuse -> xDevPLT, appfuse -> xdevplt

12. Add Demo module to demo and test.
   1) Add Demo.java and Add define in hibernate.cfg.xml.
     * If has code errors, try to right click project name, and select Maven->Enable Dependency Management.
   2) Modify pom.xml for gen Dao and Manager.
     <amp.genericCore>false</amp.genericCore> <!-- Modified by wx Ver0.0.1 for gen Dao and Manager. -->
   3) AutoGen Demo source: mvn appfuse:gen -Dentity=Demo -DdisableInstallation=true, mvn appfuse:gen -Dentity=Demo
     * Remove source: mvn appfuse:remove -Dentity=Demo
   4) Modify Demo source:
     (1) Model: Property -> @Field, OneToMany(Component) -> @IndexedEmbedded, OneToMany -> @ContainedIn
     (2) Action: Add list() in edit().
     (3) JSP pages: menu.jsp, menu-config.xml, demoList.jsp, demoForm.jsp

13. Modify for date, current, number and percent display and input.
   1) Add date, current and number format define in ApplicationResources.properties and taglibs.jsp.
   2) (Tested) Add bootstrap-datepicker.zh.js based on bootstrap-datepicker.zh-CN.js for Chinese(zh) datepicker.
      (Only supported IE9+, Chrome23.0+ and Safari5+, IE8/7 will be error.)
   3) Add CurrencyUtil.java, DoubleUtil.java, NumberUtil.java and number.js.
   4) DateUtil.java: add getSqlDate(), (Option)convertDateTimeToString(), (Option)convertStringToDateTime()
   5) (Option) DateConverter.java: modify date to dateTime

14. Modify for User module.
   1) (Option)(Tested for double save is OK) Role.java and User.java: Change GenerationType from AUTO to IDENTITY.
	(IDENTITY not supported for Oracle)
   2) User.java: add UpdateUser, UpdateDate fields, modify required fileds for easy user input.
   3) (Tested) Role.java: add @Field for role name.
   4) (Tested) User-validation.xml: add phoneNumber, website, and postalCode validation for User.
   5) UserDaoHibernate.java: add getUsersEnabled().
   6) (Tested) UserManagerImpl.java, UserManager.java: add getUserPassword(Long userId) for enable to change username
   7) RoleManagerImpl.java, UserManagerImpl.java, UserService.java: for WebService.
   8) (Tested) UserAction.java: add setEnabled(true) and check if email is null for sending mail.
   9) Add errors.url and modify errors.zip param in errors.properties.
   10) userList.jsp: 
          Add sort="list" defaultorder="descending" for displayTag.
          Add roles and phoneNumber.
   11) userForm.jsp: 
          (Tested) Remain admin user: can not be deleted, change username, enable and role.
          Remove required from passwordHint, email and website.
          Modify country display.
   12) signup.jsp: set passwordHint, email to not required.

15. Basic modification. 
   1) Add getCurrentUser() in GenericManagerImpl.java and GenericManager.java.
   2) (Tested) Add UniqueException.java in ...\service\exception.
   3) Add getFrom() in BaseAction.java.
   4) ApplicationResources.properties:
      (1) Add label and error defines.
      (2) Add xwork.default.invalid.fieldvalue for convertion error msg i18n display.
   5) (Tested) applicationContext-service.xml: add transaction for save and delete.
   6) (Tested) log4j.xml: add file log function.
   7) taglibs.jsp: add dataFormat, currencyFormat, discountFormat, percentFormat.
   8) Delete upload from mainmenu.jsp.
   9) styles.css: add span.rtf, th.headerRight
   10) Deleted SubMenu from default.jsp, and modify userList.jsp, activeUsers.jsp.

[Issues]
1. mvn test: error in testUpdateUser().
2. Can not display Chinese with native2ascii UTF8 and rtf export.
3. PasswordHint Mail send error.
4. Datetime picker input.

[xdevplt_0.1.0] - Restaurant POS 1st version
1. Add Manager roles and users.
2. Add XRP Admin, Order and Query modules with relations.
3. Add JQuery UI(1.8.20) for Order module with iPad/iPodTouch.
4. Add AJAX/JSON for XRP modules.

[xdevplt_0.2.0] - Restaurant POS 2nd version
1. Add Print module.
2. Add WebService and iOS App.

[xdevplt_0.3.0] - Restaurant POS 3rd version
1. Add discount and book functions.

Todo: 
1. Key search: Exception, Date, currency, number percent.
2. Export: 
	1) pdf i18n export. 
	2) entire list to export.
3. Timepicker.
4. Country display.

[xdevplt_future]
1. Add limit search from DB.
2. Remain editing record page using stateAttr in session when back from Form page.
3. Add Excel import.
4. Add Https for Demo Rest API.
5. Add print view and directly print.
6. Modify switchLaguage from / to current link URL.
7. Add SWT 3rd lib.
8. Add strong password check.
9. Avoid two admin user delete each other.

10. Add feedback and bulletin modules.
11. Compass multi keyword search.
12. Email to admin if errors happened.
13. Test by 100000 records for view and deleting user relations.

[TBD]
1. (TBD)(Bug16) Modify save method in GenericDaoHibernate.java for double save failed. 
2. (TBD)(Bug21) Modify dtd link of validation xml files in resource/com. 
3. (TBD)(Bug22) Modify link URL to "http://tuckey.org/res/dtds/urlrewrite3.0.dtd".
  Remove urlrewrite cache in eclipse for urlrewrite.xml.
  Go to Preferences>General>Network Connections >Cache. 
  Select the URL "http://tuckey.org/res/dtds/urlrewrite3.0.dtd" and click on Remove.
  Right click urlrewrite.xml file and select validate, then Eclipse would download and cache the file again. 
  The annoying error should be gone now.
4. (TBD)(Bug23) Add errors_en.properties(not empty) for i18n switch issue.
  (Move valication error msg from ApplicationResources.properties in Ver0.0.2.)
5. (TBD) Delete JSP error msg in messages.jsp for error msg double display.
6. (TBD) (Bug13) Add 'if (isNew) {user.setId(null);}' in save user exist exception for delete button display.
7. (TBD) (Bug15) Modify for cancel button not go back user list page, after user exist exception error.

* Modify for Displaytag.
   1) Add displaytag-export-poi and poi lib in pom.xml.(Also add struts2-json-plugin lib in pom.xml.)
   2) Add ico_file_rtf.png, and add ico_file_rtf.png define in style.css.
   3) Modify displaytag.properties for excel and rtf export. 
