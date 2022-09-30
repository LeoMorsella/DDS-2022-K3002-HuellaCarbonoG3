package utn.frba.huelladecarbono.service.InformesService;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.HuellaCarbono;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.TipoOrg;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioSectorTerritorial;

import java.util.List;
import java.util.stream.Collectors;

public class ObtenerDatosInformes {

    public String hcTotalPorST(){
        String hCtotalPorSt = "";
        List<SectorTerritorial> sectoresTerritoriales = RepositorioSectorTerritorial.getRepositorio().getSectoresTerritoriales();
        for(SectorTerritorial st : sectoresTerritoriales){
            if(st.getMunicipio().isEmpty()){
                hCtotalPorSt += "Sector de Provincia: " + st.getProvincia() + ". HC total: " + st.getHuellaCarbono() + "\n";
            }
            else{
                hCtotalPorSt += "Sector de Municipio: " + st.getMunicipio() + ". HC total: " + st.getHuellaCarbono() + "\n";
            }
        }
        return hCtotalPorSt;
    }

    public String hcTotalDeST(SectorTerritorial sector) {
        String hcTotalDeST = "";
        if(sector.getMunicipio().isEmpty()){
            hcTotalDeST += "Sector de Provincia: " + sector.getProvincia() + ". HC total: " + sector.getHuellaCarbono() + "\n";
        }
        else{
            hcTotalDeST += "Sector de Municipio: " + sector.getMunicipio() + ". HC total: " + sector.getHuellaCarbono() + "\n";
        }
        return  hcTotalDeST;
    }

    public String hcTotalPais() {
        String hCtotalPais = "";
        List<SectorTerritorial> sectoresTerritoriales = RepositorioSectorTerritorial.getRepositorio().getSectoresTerritoriales();
        for(SectorTerritorial st : sectoresTerritoriales){
            hCtotalPais += "Sector de Provincia: " + st.getProvincia() + ". HC total: " + st.getHuellaCarbono() + "\n";
        }
        return hCtotalPais;
    }

    public String hcTotalPorTipoDeOrg(){
        List<Organizacion> organizaciones = RepositorioOrganizaciones.getRepositorio().getOrganizaciones();
        String valoresGubernamental = getValores(organizaciones, TipoOrg.GUBERNAMENTAL);
        String valoresONG = getValores(organizaciones, TipoOrg.ONG);
        String valoresEmpresa = getValores(organizaciones, TipoOrg.EMPRESA);
        String valoresInstitucion = getValores(organizaciones, TipoOrg.INSTITUCION);
        return  "HC Gubernamental: " + valoresGubernamental + "\n" +
                "HC Empresa: " + valoresEmpresa + "\n" +
                "HC ONG: " + valoresONG + "\n" +
                "HC Institucion: " + valoresInstitucion;
    }

    public String hcDeOrganizacion(Organizacion organizacion) {
       String valorHCOrg = "";
       valorHCOrg += "HC Organizacion: " + organizacion.getHuellaTotal();
       return valorHCOrg;
    }

    public String getValores(List<Organizacion> organizaciones, TipoOrg tipo){
       return organizaciones.stream()
                .filter(organizacion -> organizacion.getTipo().equals(tipo))
                .map(Organizacion::getHuellaTotal)
                .collect(Collectors.summingDouble(Double::doubleValue))
                .toString();
    };
}
