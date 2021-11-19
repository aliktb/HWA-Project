"use strict";

console.log(`AddBook.js is linked`);

let addBookTitleTextInput = document.querySelector("#addBookTitleTextInput");
let addAuthorLastNameTextInput = document.querySelector(
  "#addAuthorLastNameTextInput"
);
let addAuthorFirstNameTextInput = document.querySelector(
  "#addAuthorFirstNameTextInput"
);
let addBookButton = document.querySelector("#addBookButton");
let alertAddBookDiv = document.querySelector("#alertAddBookDiv");

let addSuccessAddBookMessage = (object) => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-success col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Success!</strong> Book <strong>${object.bookTitle}</strong> has been added!`;

  alertAddBookDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let addFailAddBookMessage = () => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-danger col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Error!</strong> An error has occured!`;

  alertAddBookDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let addBookData = () => {
  let bookTitleFromInput = addBookTitleTextInput.value;
  let authorLastNameFromInput = addAuthorLastNameTextInput.value;
  let authorFirstNameFromInput = addAuthorFirstNameTextInput.value;

  let newObj = {
    authorFirstName: authorFirstNameFromInput,
    authorLastName: authorLastNameFromInput,
    bookTitle: bookTitleFromInput,
  };

  addBookToAPIFunction(newObj);
};

let addBookToAPIFunction = (object) => {
  fetch("http://localhost:9000/books/create", {
    method: "POST", // We are specifying we are POSTing data
    headers: {
      "Content-type": "application/JSON", // Telling the server we are sending JSON
    },
    body: JSON.stringify(object), // We will be creating an object and passing it in here
  }).then((response) => {
    if (response.status !== 201) {
      console.error(`Status: ${response.status}`);
      addFailAddBookMessage();
      return;
    }
    addSuccessAddBookMessage(object);
    response.json().then((data) => {
      console.log(data);
    });
  });
};

addBookButton.addEventListener("click", addBookData);
