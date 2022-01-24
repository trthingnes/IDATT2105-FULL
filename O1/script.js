// Code for list population
let words = ["arena", "floor", "haunt", "horse", "field", "float", "siege", "uncle", "dairy", "chalk", "slump", "crash", "deter", "white", "jewel", "pause", "image", "treat", "cower", "awful"]
window.onload = () => {
    let list = document.getElementById("list");

    for(let i = 0; i < 5; i++) {
        let r = Math.floor(Math.random() * words.length);
        list.innerHTML += "<li>" + words.splice(r, 1) + "</li>";
    }
}

// Code for counter button and label
let count = 0;
document.getElementById("counter-button").onclick = () => {
    document.getElementById("counter").textContent = ++count;
}

// Code for hide content button
let hidebutton = document.getElementById("hide-button");
let hidden = false;
hidebutton.onclick = () => {
    if(hidden) {
        document.getElementById("main").style.display = "block";
        hidebutton.innerText = "Gjem innhold";
    }
    else {
        document.getElementById("main").style.display = "none";
        hidebutton.innerText = "Vis innhold";
    }
    hidden = !hidden;
}