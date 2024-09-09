console.log("script loaded")

let currentTheme = getTheme();

changeTheme();



//function to change theme
function changeTheme() {
    //set to web page
    document.querySelector("html").classList.add(currentTheme);

    //set the listener to change theme button
    const changeThemeButton = document.querySelector("#theme_change_button");
    //change the text of button
    changeThemeButton.querySelector("span").textContent = currentTheme == "light" ? " Dark" : " Light";

    changeThemeButton.addEventListener("click", (event) => {
        console.log("button clicked");
        //remove the current theme 
        const oldTheme = currentTheme;
        if (currentTheme == "dark") {
            //theme ko light
            currentTheme = "light";
        }
        else {
            //theme ko dark
            currentTheme = "dark";
        }
        //localstorage mein update karenge
        setTheme(currentTheme);
        //remove the currnet theme
        document.querySelector('html').classList.remove(oldTheme);
        //add the currne theme
        document.querySelector('html').classList.add(currentTheme);
        //change the text of button
        changeThemeButton.querySelector("span").textContent = currentTheme == "light" ? " Dark" : " Light";

    });
}

//function to set theme to localstorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

//function to get theme from localstorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}