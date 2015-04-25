/**
 * This file is part of WePro.

    WePro is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    WePro is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with WePro.  If not, see <http://www.gnu.org/licenses/>.
 */
package tests.request;

import request.CityStateRequest;
import request.LongLatRequest;
import request.ZipCodeRequest;
import core.IRequestVisitor;

/**
 * A test class utility designed to check the effectiveness of the visitor
 * interface.
 * 
 * @author Forest
 * 
 */
public class TestRequestVisitor implements IRequestVisitor {
	private boolean visited;
	
	/**
	 * The constructor.  Sets the visitation to false.
	 */
	public TestRequestVisitor() {
		visited = false;
	}

	@Override
	public void visit(CityStateRequest e) {

		this.visited = true;

	}

	@Override
	public void visit(ZipCodeRequest e) {

		this.visited = true;

	}

	@Override
	public void visit(LongLatRequest e) {

		this.visited = true;

	}

	/**
	 * Returns true if this was visited. False otherwise.
	 * 
	 * @return true if visited. false otherwise.
	 */
	public boolean wasVisited() {
		return this.visited;
	}

	/**
	 * Resets visited. Convenience method.
	 */
	public void resetVisitation() {
		this.visited = false;
	}

}
