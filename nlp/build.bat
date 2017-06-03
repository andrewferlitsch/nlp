set BUILD=..\build
javac -cp %BUILD% *.java

echo off
IF %ERRORLEVEL% NEQ 1 ( 
	goto test
)

goto end

:test	

:end
rmdir epipog /s
move ..\build\epipog epipog
jar cvfe nlp.jar nlp epipog\annotations\*.class epipog\parse\*.class epipog\bow\*.class *.class
jar cvfe quora.jar quora epipog\annotations\*.class epipog\parse\*.class epipog\bow\*.class *.class
echo done
move epipog ..\build\epipog