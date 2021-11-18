"use strict";

console.log(`BookBooks.js is linked`);

let idNumberBookInput = document.querySelector("#idNumberBookInput");
let starterBookDiv = document.querySelector("#starterBookDiv");
let readAllBooksButton = document.querySelector("#readAllBooksButton");
let searchBookButton = document.querySelector("#searchBookButton");
let clearBooksButton = document.querySelector("#clearBooksButton");

let addCard = (data) => {
  let cardDiv = document.createElement("div");
  let headerDiv = document.createElement("div");
  let bodyDiv = document.createElement("div");
  let titleHeader = document.createElement("h5");
  let idPara = document.createElement("p");
  let authorLastNamePara = document.createElement("p");
  let authorFirstNamePara = document.createElement("p");

  cardDiv.classList = "card, card bg-light mb-3 m-3";
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

  starterBookDiv.appendChild(cardDiv);

  cardDiv.appendChild(headerDiv);
  headerDiv.appendChild(titleHeader);
  cardDiv.appendChild(bodyDiv);
  bodyDiv.appendChild(idPara);
  bodyDiv.appendChild(authorLastNamePara);
  bodyDiv.appendChild(authorFirstNamePara);
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
    newh5.textContent = `Book not found`;

    firstPara.classList.add("card-text");
    firstPara.textContent = `Sorry. It seems like no book with an id of ${idVal} exists.`;

    secondPara.classList.add("card-text");
    secondPara.textContent = `Try viewing all books or creating a new one`;
  } else {
    newh5.classList.add("card-title");
    newh5.textContent = `No id entered`;

    firstPara.classList.add("card-text");
    firstPara.textContent = `Sorry. It seems like you forgot to enter a valid ID`;

    secondPara.classList.add("card-text");
    secondPara.textContent = `Enter an ID and try again or view all books`;
  }
  starterBookDiv.appendChild(newDiv2);

  newDiv2.appendChild(headerDiv);
  headerDiv.appendChild(newh5);
  newDiv2.appendChild(bodyDiv);
  bodyDiv.appendChild(firstPara);
  bodyDiv.appendChild(secondPara);
};

let readByID = (idVal) => {
  fetch(`http://localhost:9000/books/getById/${idVal}`).then((response) => {
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
  fetch("http://localhost:9000/books/getAll").then((response) => {
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

readAllBooksButton.addEventListener("click", () => {
  clearScreen();
  readAll();
});

searchBookButton.addEventListener("click", () => {
  let idToCheck = idNumberBookInput.value;
  clearScreen();
  readByID(idToCheck);
  idNumberBookInput.value = "";
});

clearBooksButton.addEventListener("click", clearScreen);
