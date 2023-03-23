import facebook from '../../img/facebook.png'
import Twitter from '../../img/Twitter.png'
import Linkedin from '../../img/Linkedin.png'
import instagram from '../../img/instagram.png'
import './Logos.css'

const Logos = () => {
    return (
        <div className='containerLogos'>
            <img className='miniLogo' src={facebook} alt="facebook" />
            <img className='miniLogo' src={Twitter} alt="Twitter" />
            <img className='miniLogo' src={Linkedin} alt="Linkedin" />
            <img className='miniLogo' src={instagram} alt="instagram" />
        </div>
    )
}

export default Logos