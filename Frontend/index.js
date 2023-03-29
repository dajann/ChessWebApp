const statusDisplay = document.querySelector(".game--status");
let gameActive = true;
let firstClickedCell = 0;
let secondClickedCell = 0;
let firstClickedCellIndex;
const MESSAGE_TYPES = {
  RESTART: "restart",
  POSITIONS: "positions",
  END: "end",
  CHAT: "chat",
};
let message;
const socket = new WebSocket("ws://localhost:8080/user");

socket.addEventListener("open", function (event) {
  console.log("Connected to WebSocket server");
  handleRestartGame();
});

socket.addEventListener("message", function (event) {
  if (event.data == "CHECKMATE" || event.data == "STALEMATE") {
    console.log("Received message from server:", event.data);
    document.querySelector(".game--title").innerHTML = event.data;
    gameActive = false;
  } else if (event.data != "NO") {
    if (typeof event.data === "string" && !event.data.includes("{")) {
      displayMessage(event.data);
      console.log(event.data);
    } else {
      var data = JSON.parse(event.data);
      console.log("Received message from server:", data);
      display(data);
    }
  }
});

socket.addEventListener("close", function (event) {
  console.log("WebSocket connection closed");
});

const pieceImages = {
  wP: "Wpawn.png",
  wR: "Wrook.png",
  wH: "Wknight.png",
  wB: "Wbishop.png",
  wQ: "Wqueen.png",
  wK: "Wking.png",
  bP: "Bpawn.png",
  bR: "Brook.png",
  bH: "Bknight.png",
  bB: "Bbishop.png",
  bQ: "Bqueen.png",
  bK: "Bking.png",
};

function display(board) {
  let cells = document.querySelectorAll(".cell");
  for (let i = 0; i < cells.length; i++) {
    let cell = cells[i];
    cell.style.backgroundImage = "";
  }
  for (let i = 0; i < 8; i++) {
    for (let j = 0; j < 8; j++) {
      let position = i * 8 + j;
      let cell = document.querySelector(`[data-cell-index="${position}"]`);
      if (board[i][j] != null) {
        let pieceName = board[i][j].name;
        let pieceImage = pieceImages[pieceName];
        cell.style.backgroundImage = `url('images/${pieceImage}')`;
      }
    }
  }
  message = {
    type: MESSAGE_TYPES.END,
  };
  socket.send(JSON.stringify(message));
}
function handleCellPlayed(firstClickedCellIndex, secondClickedCellIndex) {
  let array = [firstClickedCellIndex, secondClickedCellIndex];
  message = {
    type: MESSAGE_TYPES.POSITIONS,
    positions: { x: firstClickedCellIndex, y: secondClickedCellIndex },
  };
  socket.send(JSON.stringify(message));
}

function handleCellClick(clickedCellEvent) {
  if (gameActive) {
    if (firstClickedCell == 0) {
      firstClickedCell = clickedCellEvent.target;
      firstClickedCell.style.backgroundColor = "#d1d0b7";

      firstClickedCellIndex = parseInt(
        firstClickedCell.getAttribute("data-cell-index")
      );
    } else {
      secondClickedCell = clickedCellEvent.target;
      const secondClickedCellIndex = parseInt(
        secondClickedCell.getAttribute("data-cell-index")
      );
      handleCellPlayed(firstClickedCellIndex, secondClickedCellIndex);
      firstClickedCell.style.backgroundColor = "";
      firstClickedCell = 0;
    }
  }
}

function handleRestartGame() {
  gameActive = true;
  document.querySelector(".game--title").innerHTML = "Chess";
  message = {
    type: MESSAGE_TYPES.RESTART,
  };
  socket.send(JSON.stringify(message));
}

document
  .querySelectorAll(".cell")
  .forEach((cell) => cell.addEventListener("click", handleCellClick));
document
  .querySelector(".game--restart")
  .addEventListener("click", handleRestartGame);

function selectText() {
  var textarea = document.getElementById("msg");
  var text = textarea.value;
  message = {
    type: MESSAGE_TYPES.CHAT,
    text: text,
  };
  socket.send(JSON.stringify(message));
}
function displayMessage(text) {
  var textarea = document.getElementById("recived-msg");
  textarea.value = text;
}

function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}
