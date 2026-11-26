@echo off
setlocal
cd /d "%~dp0"
if exist music.mv.db (
    echo music.mv.db already exists; keeping it.
    exit /b 0
)
if not exist ..\task6_5\music.mv.db (
    echo Create the source database first: cd ..\task6_5 ^&^& kotlin.bat run -m create
    exit /b 1
)
copy /b ..\task6_5\music.mv.db music.mv.db >nul
if errorlevel 1 exit /b 1
echo Copied the Task 6.5 database.
