@echo off
echo --------------------------- Building Project Zulu ------------------------------
set /p Input=Enter Enter Version Number:
cd ..
echo Backing up src
XCOPY forge\mcp\src forge\mcp\src-bak /E /I /Q /y
echo.
echo Copying source 
XCOPY "project-zulu\src" "forge\mcp\src\minecraft" /E /Q /y
echo.
echo Recompile
pushd forge\mcp
echo | call recompile.bat
echo Done.
echo.
echo reobfuscate_srg
echo | call reobfuscate_srg.bat
echo Done.
popd
echo.

echo Moving Art Assets to Setup Folder
XCOPY "project-zulu\assets\projectzulublock" forge\mcp\reobf\minecraft\SETUP\ProjectZuluBlocks\assets\projectzulublock /E /I /Q /y
XCOPY "project-zulu\assets\projectzulucore" forge\mcp\reobf\minecraft\SETUP\ProjectZuluCore\assets\projectzulucore /E /I /Q /y
XCOPY "project-zulu\assets\projectzulumob" forge\mcp\reobf\minecraft\SETUP\ProjectZuluMobs\assets\projectzulumob /E /I /Q /y
XCOPY "project-zulu\assets\projectzuluworld" forge\mcp\reobf\minecraft\SETUP\ProjectZuluWorld\assets\projectzuluworld /E /I /Q /y
XCOPY "project-zulu\assets\projectzuludungeon" forge\mcp\reobf\minecraft\SETUP\ProjectZuluDungeon\assets\projectzuludungeon /E /I /Q /y

XCOPY "project-zulu\assets\projectzulublock" forge\mcp\reobf\minecraft\SETUP\ProjectZuluComplete\assets\projectzulublock /E /I /Q /y
XCOPY "project-zulu\assets\projectzulucore" forge\mcp\reobf\minecraft\SETUP\ProjectZuluComplete\assets\projectzulucore /E /I /Q /y
XCOPY "project-zulu\assets\projectzulumob" forge\mcp\reobf\minecraft\SETUP\ProjectZuluComplete\assets\projectzulumob /E /I /Q /y
XCOPY "project-zulu\assets\projectzuluworld" forge\mcp\reobf\minecraft\SETUP\ProjectZuluComplete\assets\projectzuluworld /E /I /Q /y
XCOPY "project-zulu\assets\projectzuludungeon" forge\mcp\reobf\minecraft\SETUP\ProjectZuluComplete\assets\projectzuludungeon /E /I /Q /y

echo Copy Project Zulu into Complete Module in Setup 
XCOPY forge\mcp\reobf\minecraft\projectzulu forge\mcp\reobf\minecraft\SETUP\ProjectZuluComplete\projectzulu /E /I /Q /y
echo Copy Project Zulu into Core Module in Setup
XCOPY forge\mcp\reobf\minecraft\projectzulu forge\mcp\reobf\minecraft\SETUP\ProjectZuluCore\projectzulu /E /I /Q /y

echo Move Block Code from Core to Block Module
md forge\mcp\reobf\minecraft\SETUP\ProjectZuluBlocks\projectzulu\common\
MOVE forge\mcp\reobf\minecraft\SETUP\ProjectZuluCore\projectzulu\common\ProjectZulu_Blocks.class forge\mcp\reobf\minecraft\SETUP\ProjectZuluBlocks\projectzulu\common\
echo Move Mob Code from Core to Mobs Module
md forge\mcp\reobf\minecraft\SETUP\ProjectZuluMobs\projectzulu\common\
MOVE forge\mcp\reobf\minecraft\SETUP\ProjectZuluCore\projectzulu\common\ProjectZulu_Mobs.class forge\mcp\reobf\minecraft\SETUP\ProjectZuluMobs\projectzulu\common\
echo Move World Code from Core to World Module
md forge\mcp\reobf\minecraft\SETUP\ProjectZuluWorld\projectzulu\common\
MOVE forge\mcp\reobf\minecraft\SETUP\ProjectZuluCore\projectzulu\common\ProjectZulu_World.class forge\mcp\reobf\minecraft\SETUP\ProjectZuluWorld\projectzulu\common\
echo Move World Code from Core to Dungeon Module
md forge\mcp\reobf\minecraft\SETUP\ProjectZuluDungeon\projectzulu\common\
MOVE forge\mcp\reobf\minecraft\SETUP\ProjectZuluCore\projectzulu\common\ProjectZulu_Dungeon.class forge\mcp\reobf\minecraft\SETUP\ProjectZuluDungeon\projectzulu\common\

echo Move Active into Setup
pushd forge\mcp\reobf\minecraft\SETUP
echo Using 7Zip to Zip Block Module
"C:\Program Files\7-zip\7z.exe" a ProjectZuluCore%Input%.zip .\ProjectZuluCore\* -r | findstr /b /r /c:"\<Everything is Ok" /c:"\<Scanning" /c:"\<Creating archive"
"C:\Program Files\7-zip\7z.exe" a ProjectZuluBlock%Input%.zip .\ProjectZuluBlocks\* -r | findstr /b /r /c:"\<Everything is Ok" /c:"\<Scanning" /c:"\<Creating archive"
"C:\Program Files\7-zip\7z.exe" a ProjectZuluMobs%Input%.zip .\ProjectZuluMobs\* -r | findstr /b /r /c:"\<Everything is Ok" /c:"\<Scanning" /c:"\<Creating archive"
"C:\Program Files\7-zip\7z.exe" a ProjectZuluWorld%Input%.zip .\ProjectZuluWorld\* -r | findstr /b /r /c:"\<Everything is Ok" /c:"\<Scanning" /c:"\<Creating archive"
"C:\Program Files\7-zip\7z.exe" a ProjectZuluDungeon%Input%.zip .\ProjectZuluDungeon\* -r | findstr /b /r /c:"\<Everything is Ok" /c:"\<Scanning" /c:"\<Creating archive"
"C:\Program Files\7-zip\7z.exe" a -tzip ProjectZuluComplete%Input%.zip .\ProjectZuluComplete\* -r | findstr /b /r /c:"\<Everything is Ok" /c:"\<Scanning" /c:"\<Creating archive"

popd
echo Restoring src-bak
RMDIR /S /Q forge\mcp\src
REN forge\mcp\src-bak src
PAUSE
