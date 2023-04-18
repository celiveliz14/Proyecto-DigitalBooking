import facebook from '../../img/facebook.png'
import Twitter from '../../img/Twitter.png'
import Linkedin from '../../img/Linkedin.png'
import instagram from '../../img/instagram.png'
import './Logos.css'

const Logos = ({menu}) => {
    return (
        <div className={menu ? 'containerLogosMenu': 'containerLogos'}>
            <img className={menu ? 'logoSideMenu': 'miniLogo'} src={facebook} alt="facebook" />
            <img className={menu ? 'logoSideMenu': 'miniLogo'} src={Twitter} alt="Twitter" />
            <img className={menu ? 'logoSideMenu': 'miniLogo'} src={Linkedin} alt="Linkedin" />
            <img className={menu ? 'logoSideMenu': 'miniLogo'} src={instagram} alt="instagram" />
        </div>
    )
}

export default Logos