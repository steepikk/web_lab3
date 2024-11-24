document.addEventListener("DOMContentLoaded", function () {
    drawPoints(0, 0, 0);
});

function validateInput(x, y, r) {
    if (isNaN(x) || x < -4 || x > 4 || x === "") {
        return false;
    }
    if (isNaN(y) || y < -5 || y > 3 || y === "") {
        return false;
    }
    if (isNaN(r) || r < 1 || r > 4 || r === "") {
        return false;
    }
    return true;
}

function validateForm() {
    var x = document.getElementById("pointForm:x").value;
    var y = document.getElementById("pointForm:y").value;
    var r = document.getElementById("pointForm:r").value;
    var errorBlock = document.getElementById("error-message");
    var errorMessage = "";

    if (x === "" || isNaN(x)) {
        errorMessage += "X coordinate must be a valid number.\n";
    } else if (x < -4 || x > 4) {
        errorMessage += "X coordinate must be in the range of -4 to 4.\n";
    }

    if (y === "" || isNaN(y)) {
        errorMessage += "Y coordinate must be a valid number.\n";
    } else if (y < -5 || y > 3) {
        errorMessage += "Y coordinate must be in the range of -5 to 3.\n";
    }

    if (r === "" || isNaN(r)) {
        errorMessage += "R value must be a valid number.\n";
    } else if (r < 1 || r > 4) {
        errorMessage += "R value must be in the range of 1 to 4.\n";
    }

    if (errorMessage !== "") {
        errorBlock.innerHTML = errorMessage.replace(/\n/g, "<br>");
        errorBlock.style.display = "block";
        return false;
    } else {
        errorBlock.style.display = "none";
    }

    return true;
}


document.getElementById('graph').addEventListener('click', function(event) {
    const svg = document.getElementById('graph');
    const pt = svg.createSVGPoint();
    const errorMessage = document.getElementById("error-message");

    errorMessage.textContent = "";

    pt.x = event.clientX;
    pt.y = event.clientY;
    const cursorPt = pt.matrixTransform(svg.getScreenCTM().inverse());

    const rValue = parseFloat(document.getElementById('pointForm:r').value);

    if (isNaN(rValue) || rValue < 1 || rValue > 4) {
        errorMessage.textContent = 'Please enter a valid R value between 1 and 4.';
        return;
    }

    const scaledX = (cursorPt.x / 100) * rValue;
    const scaledY = -(cursorPt.y / 100) * rValue;

    document.getElementById('pointForm:x').value = scaledX.toFixed(2);
    document.getElementById('pointForm:y').value = scaledY.toFixed(2);

    const xValue = parseFloat(document.getElementById('pointForm:x').value);
    const yValue = parseFloat(document.getElementById('pointForm:y').value);

    if (!validateInput(xValue, yValue, rValue)) {
        errorMessage.textContent = 'Invalid input values. Please ensure X is between -4 and 4, Y is between -5 and 3, and R is between 1 and 4.';
        return;
    }

    drawPoints(xValue, yValue, rValue);
});

function drawPoints(x, y, r) {
    const targetDot = document.getElementById('target-dot');
    const svg = document.getElementById('graph');

    const svgWidth = svg.viewBox.baseVal.width;
    const svgHeight = svg.viewBox.baseVal.height;

    const graphX = (x / r) * (svgWidth / 2);
    const graphY = -(y / r) * (svgHeight / 2);

    if (targetDot) {
        targetDot.setAttribute("cx", graphX);
        targetDot.setAttribute("cy", graphY);
        targetDot.setAttribute("r", 3);
    }
}

function scrollToBottom() {
    window.scrollTo({
        top: document.body.scrollHeight,
        behavior: "smooth"
    });
}
