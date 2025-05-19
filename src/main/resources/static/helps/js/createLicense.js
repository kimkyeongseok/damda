import WebLicenseList from "./WebLicenseList.js";
import AppLicenseList from "./AppLicenseList.js";

createLicense("web" , WebLicenseList);
const licenseTypes = [
  {
    type: "web",
    list: WebLicenseList
  },
  {
    type: "app",
    list: AppLicenseList
  }
];
licenseTypes.forEach((license) => {
  let type = document.getElementById(license.type);
  type.style.cursor = "pointer";
  type.addEventListener("click", () => getLicense(license.type, license.list));
})

function getLicense(licenseType, licenseList) {
  colorPicker("#8e8e8e");
  createLicense(licenseType, licenseList);
}

function createLicense(licenseType, licenseList) {
  colorPicker("#8e8e8e");
  let selected = document.getElementById(licenseType);
  selected.style.color = "#000";

  let content = document.getElementById("content");
  let list = "";
  licenseList.forEach((data, index) => {
    list += `
      <div key=${index} style="border: 1px solid #8e8e8e; padding: 10px;">
        <p>
            <span style="font-size: 1.1rem;font-weight: bold;">
                ${data.name} 
            </span>
            <a href=${data.url}>${data.url}</a>
        </p> 
          <b>${data.writer}</b>
        <section>
            <details id=license_${index}>
                <summary>${data.license}</summary>
                <div data-id=license_${index} id=license_${index}_text hidden></div>
            </details>
        </section>
      </div>
      `;
  });
  content.innerHTML = list;

  licenseList.forEach((data, index) => {
    document
      .getElementById(`license_${index}`)
      .addEventListener("toggle", (e) => logItem(e, data.kind, index));
  });
}

function logItem(e, kind, index) {
  const item = document.querySelector(`[data-id=${e.target.id}]`);
  item.toggleAttribute("hidden");
  loadLicenseText(kind, index);
}

function loadLicenseText(kind, index) {
  let request = new XMLHttpRequest();
  request.open("GET", `/license/${kind}.txt`);
  request.send();
  request.onreadystatechange = () => {
    const item = document.getElementById(`license_${index}_text`);
    item.style.width = "100%;";
    item.style.height = "20rem";
    item.style.overflow = "auto";
    item.innerText = request.responseText;
  };
}
function colorPicker(color) {
  let typeBtn = document.getElementsByClassName("type_btn");
  typeBtn = Array.from(typeBtn);
  typeBtn.forEach((it) => {
    it.style.color = color;
  });
}