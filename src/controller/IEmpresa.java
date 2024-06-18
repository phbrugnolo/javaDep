package controller;

public interface IEmpresa { 
    public String listarDepartamentos();
    public String listarFuncionarios();
    public String listarFornecedores();
    public String listaGeral();
    public String calcularFolhaSalarial();
    public String listarFuncionariosEmDepartamento(String nomeDepartamento);
}
