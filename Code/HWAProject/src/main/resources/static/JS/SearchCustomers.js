"use strict";

console.log(`SearchCustomers.js is linked`);

let idNumberInput = document.querySelector("#idNumberInput");
let starterDiv = document.querySelector("#starterDiv");
let readAllButton = document.querySelector("#readAllButton");
let searchButton = document.querySelector("#searchButton");
let clearButton = document.querySelector("#clearButton");
let searchByUsernameButton = document.querySelector("#searchByUsernameButton");
let usernameSearchBook = document.querySelector("#usernameSearchBook");

let addCard = (data) => {
  let cardDiv = document.createElement("div");
  let headerDiv = document.createElement("div");
  let bodyDiv = document.createElement("div");
  let titleHeader = document.createElement("h5");
  let idPara = document.createElement("p");
  let usernamePara = document.createElement("p");

  cardDiv.classList = "card, card bg-light mb-3 m-3";
  cardDiv.style.maxWidth = "25rem";
  cardDiv.style.minWidth = "25rem";

  headerDiv.classList.add("d-flex", "flex-column", "card-header");

  bodyDiv.classList.add("card-body");

  titleHeader.classList.add("card-title");
  titleHeader.textContent = `Customers's name: ${data.firstName} ${data.lastName}`;

  idPara.classList.add("card-text");
  idPara.textContent = `Customer's ID: ${data.id}`;

  usernamePara.classList.add("card-text");
  usernamePara.textContent = `Customer's username: ${data.username}`;

  starterDiv.appendChild(cardDiv);

  cardDiv.appendChild(headerDiv);
  headerDiv.appendChild(titleHeader);
  cardDiv.appendChild(bodyDiv);
  bodyDiv.appendChild(idPara);
  bodyDiv.appendChild(usernamePara);
};

let addBookUsernameCard = (data) => {
  let cardDiv = document.createElement("div");
  let headerDiv = document.createElement("div");
  let bodyDiv = document.createElement("div");
  let titleHeader = document.createElement("h5");
  let statusBadge = document.createElement("span");
  let idPara = document.createElement("p");
  let authorLastNamePara = document.createElement("p");
  let authorFirstNamePara = document.createElement("p");

  cardDiv.classList.add("card", "bg-light", "mb-3", "m-3");
  cardDiv.style.maxWidth = "25rem";
  cardDiv.style.minWidth = "25rem";

  headerDiv.classList.add("d-flex", "flex-column", "card-header");

  bodyDiv.classList.add("card-body");

  titleHeader.classList.add("card-title");
  titleHeader.textContent = ` ${data.bookTitle}`;

  idPara.classList.add("card-text");
  idPara.textContent = `Book ID: ${data.id}`;

  authorLastNamePara.classList.add("card-text");
  authorLastNamePara.textContent = `Author's Last name: ${data.authorLastName}`;

  authorFirstNamePara.classList.add("card-text");
  authorFirstNamePara.textContent = `Author's First name: ${data.authorFirstName}`;

  if (!data.checkedOut) {
    statusBadge.classList.add("badge", "badge-success");
    statusBadge.style.float = "right";
    statusBadge.style.backgroundColor = "green";
    statusBadge.textContent = "Available";
  } else {
    statusBadge.classList.add("badge", "badge-danger");
    statusBadge.style.float = "right";
    statusBadge.style.backgroundColor = "red";
    statusBadge.textContent = "Unavailable";
  }

  starterDiv.appendChild(cardDiv);

  cardDiv.appendChild(headerDiv);
  headerDiv.appendChild(titleHeader);
  titleHeader.appendChild(statusBadge);
  cardDiv.appendChild(bodyDiv);
  bodyDiv.appendChild(idPara);
  bodyDiv.appendChild(authorLastNamePara);
  bodyDiv.appendChild(authorFirstNamePara);
};

let addNotFoundBooksCard = () => {
  let newDiv2 = document.createElement("div");
  let headerDiv = document.createElement("div");
  let bodyDiv = document.createElement("div");
  let newh5 = document.createElement("h5");
  let firstPara = document.createElement("p");
  let secondPara = document.createElement("p");

  newDiv2.classList = "card, card bg-warning mb-3 m-3";
  newDiv2.style.maxWidth = "25rem";
  newDiv2.style.minWidth = "25rem";

  headerDiv.classList.add("d-flex", "flex-column", "card-header");

  bodyDiv.classList.add("card-body");

  newh5.classList.add("card-title");
  newh5.textContent = `No book not found`;

  firstPara.classList.add("card-text");
  firstPara.textContent = `Sorry. It seems like no book with has been taken out by that username`;

  secondPara.classList.add("card-text");
  secondPara.textContent = `Try viewing all books or checkout a book`;

  starterDiv.appendChild(newDiv2);

  newDiv2.appendChild(headerDiv);
  headerDiv.appendChild(newh5);
  newDiv2.appendChild(bodyDiv);
  bodyDiv.appendChild(firstPara);
  bodyDiv.appendChild(secondPara);
};

let clearScreen = () => {
  var cardList = document.getElementsByClassName("card");

  while (cardList[0]) {
    cardList[0].parentNode.removeChild(cardList[0]);
  }
};

let addNotFoundCard = (idVal) => {
  let newDiv2 = document.createElement("div");
  let headerDiv = document.createElement("div");
  let bodyDiv = document.createElement("div");
  let newh5 = document.createElement("h5");
  let firstPara = document.createElement("p");
  let secondPara = document.createElement("p");

  newDiv2.classList = "card, card bg-warning mb-3 m-3";
  newDiv2.style.maxWidth = "25rem";
  newDiv2.style.minWidth = "25rem";

  headerDiv.classList.add("d-flex", "flex-column", "card-header");

  bodyDiv.classList.add("card-body");

  console.log(`idVal is ${idVal}`);

  if (idVal > 1) {
    newh5.classList.add("card-title");
    newh5.textContent = `Customer not found`;

    firstPara.classList.add("card-text");
    firstPara.textContent = `Sorry. It seems like no customer with an id of ${idVal} exists.`;

    secondPara.classList.add("card-text");
    secondPara.textContent = `Try viewing all customers or creating a new one`;
  } else {
    newh5.classList.add("card-title");
    newh5.textContent = `No id entered`;

    firstPara.classList.add("card-text");
    firstPara.textContent = `Sorry. It seems like you forgot to enter a valid ID`;

    secondPara.classList.add("card-text");
    secondPara.textContent = `Enter an ID and try again or view all customers`;
  }
  starterDiv.appendChild(newDiv2);

  newDiv2.appendChild(headerDiv);
  headerDiv.appendChild(newh5);
  newDiv2.appendChild(bodyDiv);
  bodyDiv.appendChild(firstPara);
  bodyDiv.appendChild(secondPara);
};

let readByID = (idVal) => {
  fetch(`http://localhost:9000/customer/getById/${idVal}`).then((response) => {
    if (response.status < 200 || response.status > 299) {
      console.error(`status: ${response.status}`);
      console.log(typeof idVal);
      addNotFoundCard(idVal);

      return;
    }
    response.json().then((json) => {
      addCard(json);
    });

    console.log("fetched");
  });
};

let readAll = () => {
  fetch("http://localhost:9000/customer/getAll").then((response) => {
    if (response.status !== 200) {
      console.error(`status: ${response.status}`);
      return;
    }

    response.json().then((json) => {
      for (let object of json) {
        addCard(object);
      }
    });

    console.log("fetched");
  });
};

readAllButton.addEventListener("click", () => {
  clearScreen();
  readAll();
});

let readByUsername = (uname) => {
  fetch(`http://localhost:9000/books/getBooksByUsername/${uname}`).then(
    (response) => {
      if (response.status < 200 || response.status > 299) {
        console.error(`status: ${response.status}`);
        console.log(typeof idVal);
        addNotFoundBooksCard();

        return;
      }
      response.json().then((json) => {
        for (let object of json) {
          addBookUsernameCard(object);
        }
      });
      console.log("fetched books by username");
    }
  );
};

searchButton.addEventListener("click", () => {
  let idToCheck = idNumberInput.value;
  clearScreen();
  readByID(idToCheck);
  idNumberInput.value = "";
});

clearButton.addEventListener("click", clearScreen);

searchByUsernameButton.addEventListener("click", () => {
  clearScreen();
  readByUsername(usernameSearchBook.value);
});
