var url = "/api/admin/management/users";

$(() => {
  $.ajax({
    url: `${url}`,
    method: "GET",
    success: (data) => {
      table = $("#dataTable").DataTable({
        destroy: true, //테이블 데이터 초기화
        scrollCollapse: true,
        data: data,
        order: [4, "desc"],
        scrollY: "70vh",
        pageLength: 20,
        lengthMenu: [0, 10, 20],
        columns: [
          { data: "id" },
          { data: "nickName" },
          { data: "userId" },
          { data: "signUpDate" },
          { data: "loginDate" },
          { data: "userAgent" },
          { data: "deactivate" },
        ],
        columnDefs: [
          {
            targets: 6,
            render: (data) => {
              if (data) {
                return "<button id=btn_activeUser>활성화</button>";
              } else {
                return "<button id=btn_deactivateUser>비활성화</button>";
              }
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

$("#dataTable > tbody").on("click", "#btn_deactivateUser", function () {
  if (!confirm("해당 유저를 비활성화 하시겠습니까?")) {
    return false;
  } else {
    var id = table.row($(this).parents("tr")).data().id;
    $.ajax({
      url: `${url}/deactivate/${id}`,
      type: "PUT",
      success: function () {
        document.location.reload();
      },
    });
  }
});
$("#dataTable > tbody").on("click", "#btn_activeUser", function () {
  if (!confirm("해당 유저를 활성화 하시겠습니까?")) {
    return false;
  } else {
    var id = table.row($(this).parents("tr")).data().id;
    $.ajax({
      url: `${url}/active/${id}`,
      type: "PUT",
      success: function () {
        document.location.reload();
      },
    });
  }
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
