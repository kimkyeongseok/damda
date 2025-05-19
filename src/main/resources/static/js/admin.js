function login() {
  let adminId = document.getElementById("adminId").value;
  let adminPw = document.getElementById("adminPw").value;

  $.ajax({
    url: `/api/admin/login`,
    type: "POST",
    data: {
      adminId: adminId,
      adminPw: adminPw,
    },
    success: (res) => {
      alert(res.message);
      document.location.href = "/admin/page/main";
    },
    error: (error) => {
      alert(error.responseJSON.message);
    },
  });
}

function logout() {
  $.ajax({
    url: `/api/admin/logout`,
    type: "GET",
    success: (res) => {
      document.location.replace("/admin/page/login");
    },
    error: (error) => {},
  });
}
