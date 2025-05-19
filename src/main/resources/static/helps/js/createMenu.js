const menuWrap = document.getElementById("menu_wrap");
const list = document.querySelectorAll("#wrap > p > strong");

let menu = "";
list.forEach((e, index) => {
  e.setAttribute("id", index);
  e.setAttribute("name", index);
  const text = document.getElementById(index).innerText;
  menu +=
    "<p style=fontSize: 2rem;><a href=#" + index + ">" + text + "</a></p>";
});
menuWrap.innerHTML = menu;
