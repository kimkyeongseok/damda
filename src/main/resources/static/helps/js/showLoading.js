function showLoading(isShow) {
  if (isShow) {
    button.style.pointerEvents = "none";
    document.getElementById("loading").style.visibility = "visible";
    document.getElementById("buttonText").style.visibility = "hidden";
  } else {
    document.getElementById("loading").style.visibility = "hidden";
    document.getElementById("buttonText").style.visibility = "visible";
    button.style.pointerEvents = "auto";
  }
}
