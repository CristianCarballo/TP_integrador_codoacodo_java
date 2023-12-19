const ticket = 200;
const descEstudiante = 0.8;
const descTrainee = 0.5;
const descJunior = 0.15;

const cantidad = document.getElementById("cantidad");
const categoria = document.getElementById("categoria");
const totalPagar = document.getElementById("totalAPagar");
const btnResumen = document.getElementById("resumen");

function calcularPago() {
  let total = cantidad.value * ticket;
  switch (categoria.value) {
    case "estudiante":
      total = total - total * descEstudiante;
      break;
    case "trainee":
      total = total - total * descTrainee;
      break;
    case "junior":
      total = total - total * descJunior;
      break;
    default:
      total;
      break;
  }
  totalPagar.textContent = `Total a Pagar: $ ${total}`;
}

btnResumen.addEventListener("click", (e) => {
  e.preventDefault();
  calcularPago();
});

document.addEventListener("DOMContentLoaded", function () {
  const btnVolver = document.getElementById("volver");
  if (btnVolver) {
    btnVolver.addEventListener("click", function () {
      window.location.href = "index.html";
    });
  }
});
