var url = "/api/admin/management/product";

function check(isFree) {
  let price = document.getElementById("price");
  if (isFree === "true") {
    price.value = 0;
  }
  if (isFree === "false") {
    price.value = "";
  }
}

$(() => {
  $.ajax({
    url: `${url}`,
    method: "GET",
    success: (data) => {
      table = $("#dataTable").DataTable({
        destroy: true, //테이블 데이터 초기화
        scrollCollapse: true,
        data: data,
        order: [5, "desc"],
        scrollY: "70vh",
        pageLength: 20,
        lengthMenu: [0, 10, 20],
        columns: [
          { data: "id" },
          { data: "name" },
          { data: "preview" },
          { data: "category" },
          { data: "price" },
          { data: "uploadDate" },
          { data: "isFree" },
          {
            data: "",
            defaultContent:
              "<button id=btn_updateProduct>수정</button> <button id=btn_deleteProduct>삭제</button>",
          },
        ],
        columnDefs: [
          {
            targets: 2,
            render: (data) => {
              return `<img src=${data} style="width: 4rem" />`;
            },
          },
          {
            targets: 6,
            render: (data) => {
              return data ? "무료" : "유료";
            },
          },
        ],
      });
    },
    error: () => {
      alert("로그인이 필요합니다.");
      document.location.href = "/admin/page/login";
    },
  });
});

function create() {
  var form = $("#createForm");
  var formData = new FormData(form[0]);

  function valid(formData) {
    for (var entry of formData.entries()) {
      if (entry[1].length === 0 || entry[1].size === 0) {
        return false;
      }
    }
    return true;
  }
  if (valid(formData)) {
    $.ajax({
      url: url,
      type: "POST",
      data: formData,
      processData: false,
      contentType: false,
      timeout: 8000,
    }).then(() => {
      document.location.reload();
    });
  }
}

$("#dataTable > tbody").on("click", "#btn_updateProduct", function () {
  var object = table.row($(this).parents("tr")).data();
  $("#update-modal").modal("toggle");
  $(".id").val(object.id);
  $(".name").val(object.name);
  $(".preview").attr("src", object.preview);
  $(".price").val(object.price);
  $("input:radio[name=isFree]:input[value=" + object.isFree + "]").prop(
    "checked",
    true
  );
  $("input:radio[name=isFree]").change();
  $("input:radio[name=category]:input[value=" + object.category + "]").prop(
    "checked",
    true
  );
  $("input:radio[name=category]").change();
});

function update() {
  var form = $("#updateForm");
  var formData = new FormData(form[0]);
  $.ajax({
    url: url,
    type: "PUT",
    data: formData,
    processData: false,
    contentType: false,
    statusCode: {
      400: (res) => {
        alert(res.responseText);
      },
      409: (res) => {
        alert(res.responseText);
      },
    },
    success: function () {
      document.location.reload();
    },
  });
}

$("#dataTable > tbody").on("click", "#btn_deleteProduct", function () {
  if (!confirm("삭제하시겠습니까?")) {
    return false;
  } else {
    let id = table.row($(this).parents("tr")).data().id;
    $.ajax({
      url: `${url}/id/${id}`,
      type: "DELETE",
      success: function () {
        document.location.reload();
      },
    });
  }
});
