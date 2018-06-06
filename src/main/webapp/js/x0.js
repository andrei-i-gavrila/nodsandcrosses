let playing = false;

let interval = setInterval(getBoard, 500);


$('#game > div').each((i, cell) => {
    console.log(cell);
    $(cell).click(() => {
        if (playing) {
            $.post('/game', {move: i})
                .then(getBoard)
        }
    });
});


function getBoard() {
    $.getJSON('/game').then(response => {
        makeBoard(response.game.gameState);
        $('#message').text(response.status);
        if(response.status === "Playing") {
            playing = true;
        }
    }).catch(() => clearInterval(interval))
}

function makeBoard(boardState) {
    $('#game').children().each((i, cell) => {
        if (boardState[i] !== '-') {
            $(cell).text(boardState[i])
        }
    })
}
