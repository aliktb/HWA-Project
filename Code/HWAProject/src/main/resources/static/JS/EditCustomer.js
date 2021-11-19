"use strict";

let IDToUpdateNumberInput = document.querySelector("#IDToUpdateNumberInput");
let newFirstNameTextInput = document.querySelector("#newFirstNameTextInput");
let newLastNameTextInput = document.querySelector("#newLastNameTextInput");
let newUsernameTextInput = document.querySelector("#newUsernameTextInput");

let updateButton = document.querySelector("#updateCustomerButton");
let deleteButton = document.querySelector("#deleteCustomerButton");
let alertUpdateDiv = document.querySelector("#alertUpdateCustomerDiv");

let addSuccessUpdateMessage = (object) => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-success col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Success!</strong> Customer <strong>${object.firstName} ${object.lastName}</strong> has been updated!`;

  alertUpdateDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let addFailUpdateMessage = () => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-danger col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Error!</strong> An error has occured!`;

  alertUpdateDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let addSuccessDeleteMessage = (idVal) => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-success col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Success!</strong> Customer with ID <strong>${idVal}</strong> has been deleted!`;

  alertUpdateDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let createUpdatedObject = () => {
  let idVal = IDToUpdateNumberInput.value;
  let updatedFirstNameFromInput = newFirstNameTextInput.value;
  let updatedLastNameFromInput = newLastNameTextInput.value;
  let updatedUsernameFromInput = newUsernameTextInput.value;

  let updatedObj = {
    firstName: updatedFirstNameFromInput,
    lastName: updatedLastNameFromInput,
    username: updatedUsernameFromInput,
  };

  updateFuntion(idVal, updatedObj);
};

let updateFuntion = (idToUpdate, updatedObject) => {
  fetch(`http://localhost:9000/customer/update/${idToUpdate}`, {
    method: "PUT",
    body: JSON.stringify(updatedObject),
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  }).then((response) => {
    if (response.status < 200 || response.status > 299) {
      console.error(`status: ${response.status}`);
      addFailUpdateMessage();
      console.log(`There was an error trying to update`);
      return;
    }
    addSuccessUpdateMessage(updatedObject);
    response.json().then((json) => console.log(json));
  });
};

let deleteFunction = (idVal) => {
  fetch(`http://localhost:9000/customer/delete/${idVal}`, {
    method: "DELETE",
  }).then((response) => {
    if (response.status == 500) {
      console.log(response.status);
      console.error(`Status: ${response.statusText}`);
      addFailUpdateMessage();
      console.log(`the delete response failed`);
      return;
    }
    addSuccessDeleteMessage(idVal);
    response.json().then((data) => {
      console.log(data);
    });
  });
};

updateButton.addEventListener("click", createUpdatedObject);
deleteButton.addEventListener("click", () => {
  deleteFunction(IDToUpdateNumberInput.value);
});
