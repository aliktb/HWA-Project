"use strict";

let IDToBorrowBookNumberInput = document.querySelector(
  "#IDToBorrowBookNumberInput"
);
let IDOfCustomerToBorrowNumberInput = document.querySelector(
  "#IDOfCustomerToBorrowNumberInput"
);

let checkoutBookButton = document.querySelector("#checkoutBookButton");
let returnBookButton = document.querySelector("#returnBookButton");
let alertBorrowBookDiv = document.querySelector("#alertBorrowBookDiv");

let addSuccessCheckoutMessage = (book, customer) => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-success col-sm mt-4 new-alert";

  console.log(`book is ${book}`);

  newDiv.innerHTML = `<strong>Success!</strong> Book <strong>${book.bookTitle}</strong> has been checked out to <strong>${customer.firstName} ${customer.lastName}</strong>`;

  alertBorrowBookDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 6000);
};

let addSuccessReturnMessage = (book) => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-success col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Success!</strong> Book <strong>${book.bookTitle}</strong> has been returned!`;

  alertBorrowBookDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let addFailBorrowMessage = () => {
  let newDiv = document.createElement("div");

  newDiv.classList = "alert alert-danger col-sm mt-4 new-alert";

  newDiv.innerHTML = `<strong>Error!</strong> An error has occured!`;

  alertBorrowBookDiv.appendChild(newDiv);

  setTimeout(function () {
    $(".new-alert").fadeOut(400);
  }, 4000);
};

let readBookByID = (idVal) => {
  fetch(`http://localhost:9000/books/getById/${idVal}`).then((response) => {
    if (response.status < 200 || response.status > 299) {
      console.error(`status: ${response.status}`);
      console.log(typeof idVal);

      return;
    }
    response.json().then((json) => {
      console.log(json);
      //return json;
      createCheckoutObject(json);
    });

    console.log("book fetched");
  });
};

let createCheckoutObject = (Book) => {
  let idBookVal = IDToBorrowBookNumberInput.value;
  let idCustomerid = IDOfCustomerToBorrowNumberInput.value;

  console.log(Book);

  let updatedObj = {
    id: idCustomerid,
  };

  checkoutFuntion(idBookVal, updatedObj, Book);
};

let checkoutFuntion = (bookID, updatedObject, newBook) => {
  fetch(`http://localhost:9000/books/checkout/${bookID}`, {
    method: "PUT",
    body: JSON.stringify(updatedObject),
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  }).then((response) => {
    if (response.status < 200 || response.status > 299) {
      console.error(`status: ${response.status}`);
      addFailCheckoutMessage();
      console.log(`There was an error trying to checkout`);
      return;
    }
    console.log(
      `line 103 ${IDOfCustomerToBorrowNumberInput.value}, ${newBook}`
    );
    readCustomerByID(IDOfCustomerToBorrowNumberInput.value, newBook);
    //addSuccessCheckoutMessage(newBook);
    response.json().then((json) => console.log(json));
  });
};

let readCustomerByID = (idVal, newBook) => {
  fetch(`http://localhost:9000/customer/getById/${idVal}`).then((response) => {
    if (response.status < 200 || response.status > 299) {
      console.error(`status: ${response.status}`);
      console.log(typeof idVal);

      return;
    }
    response.json().then((json) => {
      addSuccessCheckoutMessage(newBook, json);
    });

    console.log("customer fetched");
  });
};

let readBookReturnByID = (bookToReturnID) => {
  fetch(`http://localhost:9000/books/getById/${bookToReturnID}`).then(
    (response) => {
      if (response.status < 200 || response.status > 299) {
        console.error(`status: ${response.status}`);
        console.log(typeof idVal);

        return;
      }
      response.json().then((json) => {
        console.log(json);
        //return json;
        returnBookFunction(bookToReturnID, json);
      });

      console.log("book fetched");
    }
  );
};

let returnBookFunction = (bookID, newBook) => {
  fetch(`http://localhost:9000/books/return/${bookID}`, {
    method: "PUT",

    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  }).then((response) => {
    if (response.status < 200 || response.status > 299) {
      console.error(`status: ${response.status}`);
      addFailCheckoutMessage();
      console.log(`There was an error trying to return a book`);
      return;
    }

    addSuccessReturnMessage(newBook);
    response.json().then((json) => console.log(json));
  });
};

checkoutBookButton.addEventListener("click", () => {
  readBookByID(IDToBorrowBookNumberInput.value);
});

returnBookButton.addEventListener("click", () => {
  readBookReturnByID(IDToBorrowBookNumberInput.value);
});
