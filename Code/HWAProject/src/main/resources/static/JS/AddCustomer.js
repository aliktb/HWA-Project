"use strict";

console.log(`AddCustomer.js is linked`);

let firstNameTextInput = document.querySelector("#firstNameTextInput");
let lastNameTextInput = document.querySelector("#lastNameTextInput");
let usernameTextInput = document.querySelector("#usernameTextInput");
let createNewButton = document.querySelector("#createNewButton");
let successDiv = document.querySelector("#successDiv");

let addSuccessMessage = (object) => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-success col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Success!</strong> Customer <strong>${object.firstName} ${object.lastName}</strong> has been added!`;

  successDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let addFailMessage = () => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-danger col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Error!</strong> An error has occured!`;

  successDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let postData = () => {
  let firstNameFromInput = firstNameTextInput.value;
  let lastNameFromInput = lastNameTextInput.value;
  let usernameFromInput = usernameTextInput.value;

  let newObj = {
    firstName: firstNameFromInput,
    lastName: lastNameFromInput,
    username: usernameFromInput,
  };

  console.log(`postData running`);
  console.log(`firstName is ${newObj.firstName}`);
  console.log(`lastName is ${newObj.lastName}`);
  console.log(`username is ${usernameTextInput.value}`);

  postFunction(newObj);
};

let postFunction = (object) => {
  fetch("http://localhost:9000/customer/create", {
    method: "POST", // We are specifying we are POSTing data
    headers: {
      "Content-type": "application/JSON", // Telling the server we are sending JSON
    },
    body: JSON.stringify(object), // We will be creating an object and passing it in here
  }).then((response) => {
    if (response.status !== 201) {
      console.error(`Status: ${response.status}`);
      addFailMessage();
      return;
    }
    addSuccessMessage(object);
    response.json().then((data) => {
      console.log(data);
    });
  });
};

createNewButton.addEventListener("click", postData);
