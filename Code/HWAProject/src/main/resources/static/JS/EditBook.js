"use strict";

let IDToUpdateBookNumberInput = document.querySelector(
  "#IDToUpdateBookNumberInput"
);
let newAuthorFirstNameTextInput = document.querySelector(
  "#newAuthorFirstNameTextInput"
);
let newAuthorLastNameTextInput = document.querySelector(
  "#newAuthorLastNameTextInput"
);
let newBookTitleTextInput = document.querySelector("#newBookTitleTextInput");

let updateBookButton = document.querySelector("#updateBookButton");
let deleteBookButton = document.querySelector("#deleteBookButton");
let alertUpdateBookDiv = document.querySelector("#alertUpdateBookDiv");

let addSuccessUpdateMessage = (object) => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-success col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Success!</strong> Book <strong>${object.bookTitle}</strong> has been updated!`;

  alertUpdateBookDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let addFailUpdateMessage = () => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-danger col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Error!</strong> An error has occured!`;

  alertUpdateBookDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let addSuccessDeleteMessage = (idVal) => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-success col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Success!</strong> Book with ID <strong>${idVal}</strong> has been deleted!`;

  alertUpdateBookDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let createUpdatedObject = () => {
  let idVal = IDToUpdateBookNumberInput.value;
  let updatedAuthorLastNameFromInput = newAuthorLastNameTextInput.value;
  let updatedAuthorFirstNameFromInput = newAuthorFirstNameTextInput.value;

  let updatedBookTitleFromInput = newBookTitleTextInput.value;

  let updatedObj = {
    authorLastName: updatedAuthorLastNameFromInput,
    authorFirstName: updatedAuthorFirstNameFromInput,
    bookTitle: updatedBookTitleFromInput,
  };

  updateFuntion(idVal, updatedObj);
};

let updateFuntion = (idToUpdate, updatedObject) => {
  fetch(`http://localhost:9000/books/update/${idToUpdate}`, {
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
  fetch(`http://localhost:9000/books/delete/${idVal}`, {
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

updateBookButton.addEventListener("click", createUpdatedObject);
deleteBookButton.addEventListener("click", () => {
  deleteFunction(IDToUpdateBookNumberInput.value);
});
