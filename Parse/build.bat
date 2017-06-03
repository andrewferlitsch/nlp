set BUILD=..\build
javac -d %BUILD% -cp %BUILD% *.java

echo off
IF %ERRORLEVEL% NEQ 1 ( 
	goto test
)

goto end

:test	


:end
echo done